package com.example.inverontoryservice.controller;

import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventoryAllocation")
public class AllocationController {

    @Autowired
    private AllocationService allocationService;


}
