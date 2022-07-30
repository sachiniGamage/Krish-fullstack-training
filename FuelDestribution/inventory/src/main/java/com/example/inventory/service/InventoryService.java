package com.example.inventory.service;

import com.example.inventory.model.FuelType;
import com.example.inventory.model.Inventory;
import com.example.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory storeFuel(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventory;
    }

    public List<Inventory> getInventoryDetails() {
        List<Inventory> list = inventoryRepository.findAll();
        return list;
    }

    public Inventory getInventoryDetailsByFuelType(FuelType fuelType) {
        Inventory inventory = inventoryRepository.findAllByFuelTypeEquals(fuelType);
        return inventory;
    }
}
