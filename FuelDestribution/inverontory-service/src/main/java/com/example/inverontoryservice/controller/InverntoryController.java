package com.example.inverontoryservice.controller;

import com.example.inverontoryservice.model.FuelType;
import com.example.inverontoryservice.model.Inventory;
import com.example.inverontoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InverntoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/store")
    public Inventory storeFuel(@RequestBody Inventory inventory){
        Inventory inventory1 = inventoryService.storeFuel(inventory);
        return inventory1;
    }
//
//    @GetMapping(path = "/all")
//    public List<Inventory> getInventoryDetails(){
//        List<Inventory> inventoryList = inventoryService.getInventoryDetails();
//        return  inventoryList;
//    }
//
//    @GetMapping(path = "/findByFuelType")
//    public Inventory getInventoryDetailsByFuelType(@RequestParam(value = "fuelType") FuelType fuelType){
//        Inventory inventory = inventoryService.getInventoryDetailsByFuelType(fuelType);
//        return  inventory;
//    }

}
