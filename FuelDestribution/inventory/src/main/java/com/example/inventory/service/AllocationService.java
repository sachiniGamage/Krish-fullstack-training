package com.example.inventory.service;

import com.example.inventory.model.Allocation;
import com.example.inventory.model.FuelType;
import com.example.inventory.repository.AllocationRepository;
import com.example.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepository;

    public Allocation allocate(Allocation allocation) {
        allocationRepository.save(allocation);
        return allocation;
    }

    public List<Allocation> getAllAllocations() {
        List<Allocation> allocationList = allocationRepository.findAll();
        return allocationList;
    }


    public Allocation getAllocationById(int id) {
        Optional<Allocation> allocation = allocationRepository.findById(id);
        if(allocation.isPresent()){
            return allocation.get();
        }else{
            return new Allocation();
        }
    }

    //todo: get order capacity
/*
    //check availability
    public double fuelAvailability(FuelType fuelType){
        InventoryService inventoryService = new InventoryService();
        double currentInventoryCapacity = inventoryService.getInventoryDetailsByFuelType(fuelType).getCapacity();
//        double orderLiters = allocationRepository.
    }
*/
    public Allocation getOrderCapacityByAllocationId(int id) {
        Optional<Allocation> orderCapacity = allocationRepository.findById(id);
        if(orderCapacity.isPresent()){
            return orderCapacity.get();
        }else{
            return new Allocation();
        }
    }
}
