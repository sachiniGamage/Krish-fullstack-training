package com.example.inverontoryservice.service;

import com.example.order.model.FuelType;
import com.example.inverontoryservice.model.Inventory;
import com.example.inverontoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory storeFuel(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventory;
    }

    public List<Inventory> getInventoryDetails() {
        List<Inventory> list = inventoryRepository.findAll();
        return list;
    }

    public Inventory getInventoryDetailsByFuelType(FuelType fuelType) {
        Inventory inventory = inventoryRepository.findByFuelTypeEquals(fuelType);
        return inventory;
    }

    public void updateFuelCapacityDB(Inventory inventory, double capacity){
        inventory.setCapacity(capacity);
        inventoryRepository.save(inventory);
    }
}
