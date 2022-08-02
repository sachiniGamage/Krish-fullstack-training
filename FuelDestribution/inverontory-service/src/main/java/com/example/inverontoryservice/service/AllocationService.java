package com.example.inverontoryservice.service;

import com.example.inverontoryservice.KafkaListener;
import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.order.model.FuelType;
import com.example.inverontoryservice.model.Inventory;
import com.example.inverontoryservice.repository.AllocationRepository;
import com.example.order.model.Orders;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AllocationService {

    double available92 = 30_000;
    double available95 = 60_000;
    double availableDiesel = 90_000;
    double availableSuperDiesel = 100_000;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private AllocationRepository allocationRepository;

    public void saveAllocation(Allocation allocation, Orders orders){

        boolean allocate = checkAvailability(orders.getFuelCapacity(), orders.getFuelType());
        CurrentStatus status;
        if(allocate == true){
            status = CurrentStatus.ALLOCATED;
        }else{
            status = CurrentStatus.notAllocated;
        }
        System.out.println(status.toString());
        allocation.setOrder(orders);
        allocation.setAllocated(status);
        allocationRepository.save(allocation);
        kafkaTemplate.send("AllocationComplete", allocation.toString());
    }

    public boolean checkAvailability(double capacity,FuelType fuelType){
        //check the current stock
        Inventory inventory = inventoryService.getInventoryDetailsByFuelType(fuelType);
        double stock =inventory.getCapacity();

        System.out.println("current stock: " + stock +" - fuel type : " + fuelType);

        double remain92,remain95,diesel,superDiesel;

        if(fuelType == FuelType.Patrol92) {
            if (stock > capacity) {
                remain92 = stock - capacity;
                inventoryService.updateFuelCapacityDB(inventory,remain92);
                System.out.println("can complete the order 92");

                return true;
            } else {
                kafkaTemplate.send("cannotAllocate", "Order is fail");
                System.out.println("cannot complete the order - 92");
                return false;
            }
        }else if(fuelType == FuelType.Patrol95){
            if (stock > capacity) {
                remain95 = stock - capacity;
                inventoryService.updateFuelCapacityDB(inventory,remain95);
                System.out.println("can complete the order 95");
                return true;
            } else {
                kafkaTemplate.send("cannotAllocate", "Order is fail");
                System.out.println("cannot complete the order - 95");
                return false;
            }
        }else if(fuelType == FuelType.Diesel){
            if (stock > capacity) {
                diesel = stock - capacity;
                inventoryService.updateFuelCapacityDB(inventory,diesel);
                System.out.println("can complete the order Diesel");
                return true;
            } else {
                kafkaTemplate.send("cannotAllocate", "Order is fail");
                System.out.println("cannot complete the order - Diesel");
                return false;
            }
        }else if(fuelType == FuelType.SuperDiesel){
            if (stock > capacity) {
                superDiesel = stock - capacity;
                inventoryService.updateFuelCapacityDB(inventory,superDiesel);
                System.out.println("can complete the order Super Diesel");
                return true;
            } else {
                kafkaTemplate.send("cannotAllocate", "Order is fail");
                System.out.println("cannot complete the order - Super Diesel");
                return false;
            }
        }else{
            kafkaTemplate.send("cannotAllocate", "Order is fail");
            System.out.println("cannot complete the order");
            return false;
        }
    }

    public Orders createOrder(int id, String name, String location, int gasStationId, double capacity, com.example.order.model.FuelType fuelType){
        Orders orders = new Orders();
        orders.setOrderId(id);
        orders.setCpcOwnerName(name);
        orders.setLocation(location);
        orders.setGasStationId(gasStationId);
        orders.setFuelCapacity(capacity);
        orders.setFuelType(fuelType);

        return orders;
    }

    public void splitJsonMsg(String msg){

        String[] split1 = msg.split("Orders\\(");
        String part2 = split1[1];

        String[] split2 = part2.split("\\)");
        String part3 = split2[0];

        String[] split3 = part3.split(", ");
        List<String> list = new ArrayList<>();
        for (String string : split3) {
            list.add(string);
        }
        System.out.println(list);

        List<String> orderVariables = new ArrayList<>();
        List<String> orderValues = new ArrayList<>();
        for (String string : list) {
            String[] split4 = string.split("=");
            orderVariables.add(split4[0]);
        }
        for (String string : list) {
            String[] split4 = string.split("=");
            orderValues.add(split4[1]);
        }
        System.out.println(orderVariables);
        System.out.println(orderValues);

        int OrderId = Integer.parseInt(orderValues.get(0));
        String name = orderValues.get(1);
        String location = orderValues.get(2);
        int gasStationId = Integer.parseInt(orderValues.get(3));
        double capacity = Double.parseDouble(orderValues.get(4));
        FuelType fuelType = FuelType.valueOf(orderValues.get(5));

        Allocation allocation = new Allocation();
        saveAllocation(allocation ,createOrder(OrderId,name,location,gasStationId,capacity,fuelType));
    }

}
