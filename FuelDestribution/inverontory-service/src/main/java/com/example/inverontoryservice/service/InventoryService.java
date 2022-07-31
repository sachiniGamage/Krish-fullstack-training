package com.example.inverontoryservice.service;

import com.example.inverontoryservice.model.Inventory;
import com.example.inverontoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public Inventory storeFuel(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventory;
    }

    public List<Inventory> getInventoryDetails() {
        List<Inventory> list = inventoryRepository.findAll();
        return list;
    }
}
