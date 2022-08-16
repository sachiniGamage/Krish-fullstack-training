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

    @Autowired
    private KafkaTemplate<String, Allocation> kafkaTemplate;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private AllocationRepository allocationRepository;

    public void getOrderData(Orders order){
        Allocation allocation = new Allocation();
        saveAllocation(allocation ,order);

    }

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
        kafkaTemplate.send("AllocationComplete", allocation);
    }

    public boolean checkAvailability(double capacity,FuelType fuelType){
        //check the current stock
        Inventory inventory = inventoryService.getInventoryDetailsByFuelType(fuelType);
        double stock =inventory.getCapacity();

        System.out.println("current stock: " + stock +" - fuel type : " + fuelType);

        double remain92,remain95,diesel,superDiesel;
        Allocation allocation;

        if(fuelType == FuelType.Patrol92) {
            if (stock > capacity) {
                remain92 = stock - capacity;
                inventoryService.updateFuelCapacityDB(inventory,remain92);
                System.out.println("can complete the order 92");

                return true;
            } else {
//                kafkaTemplate.send("cannotAllocate", null);
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
//                kafkaTemplate.send("cannotAllocate", null);
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
//                kafkaTemplate.send("cannotAllocate", null);
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
//                kafkaTemplate.send("cannotAllocate", );
                System.out.println("cannot complete the order - Super Diesel");
                return false;
            }
        }else{
//            kafkaTemplate.send("cannotAllocate", null);
            System.out.println("cannot complete the order");
            return false;
        }
    }

}
