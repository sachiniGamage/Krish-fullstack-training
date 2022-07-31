package com.example.inverontoryservice.repository;

import com.example.order.model.FuelType;
import com.example.inverontoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByFuelTypeEquals(FuelType fuelType);
}
