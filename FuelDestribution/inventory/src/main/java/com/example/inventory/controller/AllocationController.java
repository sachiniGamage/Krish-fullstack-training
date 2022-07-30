package com.example.inventory.controller;

import com.example.inventory.model.Allocation;
import com.example.inventory.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocation")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;

    @PostMapping(path = "/allocate")
    public Allocation allocate(@RequestBody Allocation allocation){
        Allocation allocation1 = allocationService.allocate(allocation);
        return allocation1;
    }

    @GetMapping(path = "/all")
    public List<Allocation> getAllAllocations(){
        List<Allocation> allocationList = allocationService.getAllAllocations();
        return allocationList;
    }

    @GetMapping(path = "/allocate-by-id", params = "id")
    public Allocation getAllocationById(@RequestParam(value = "id")int id){
        Allocation allocation = allocationService.getAllocationById(id);
        return allocation;
    }

    @GetMapping(path = "/getOderCapacity")
    public Allocation getOrderCapacityByAllocationId(@RequestParam(value = "id") int id){
        Allocation orderCapacity = allocationService.getOrderCapacityByAllocationId(id);
        return orderCapacity;
    }

}
