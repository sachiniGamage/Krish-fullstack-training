package com.example.inventory.controller;

import com.example.inventory.model.FuelType;
import com.example.inventory.model.Inventory;
import com.example.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/store")
    public Inventory storeFuel(@RequestBody Inventory inventory){
        Inventory inventory1 = inventoryService.storeFuel(inventory);
        return inventory1;
    }

    @GetMapping(path = "/all")
    public List<Inventory> getInventoryDetails(){
        List<Inventory> inventoryList = inventoryService.getInventoryDetails();
        return  inventoryList;
    }

    @GetMapping(path = "/findByFuelType")
    public Inventory getInventoryDetailsByFuelType(@RequestParam(value = "fuelType") FuelType fuelType){
        Inventory inventory = inventoryService.getInventoryDetailsByFuelType(fuelType);
        return  inventory;
    }

}
