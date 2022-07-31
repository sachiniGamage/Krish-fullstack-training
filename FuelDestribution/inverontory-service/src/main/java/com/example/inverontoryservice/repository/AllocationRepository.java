package com.example.inverontoryservice.repository;

import com.example.inverontoryservice.model.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
}
