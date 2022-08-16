package com.example.inverontoryservice.controller;

import com.example.order.model.FuelType;
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

}
