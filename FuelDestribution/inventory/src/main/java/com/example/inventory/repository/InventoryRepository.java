package com.example.inventory.repository;

import com.example.inventory.model.FuelType;
import com.example.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findAllByFuelTypeEquals(FuelType fuelType);
}
