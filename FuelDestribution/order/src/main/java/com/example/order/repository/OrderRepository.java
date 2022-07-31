package com.example.order.repository;

import com.example.order.model.FuelType;
import com.example.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    Orders findAllByFuelTypeEquals(FuelType fuelType);
}
