package com.example.inverontoryservice.controller;

import com.example.inverontoryservice.model.Inventory;
import com.example.inverontoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public class InverntoryController {

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


}
