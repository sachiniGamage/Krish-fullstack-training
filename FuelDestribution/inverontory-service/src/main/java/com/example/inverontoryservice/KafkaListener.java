package com.example.inverontoryservice;

import com.example.inverontoryservice.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @Autowired
    private AllocationService allocationService;

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "NewOrder",
            groupId = "groupId2"
    )
    void listener(String data) {
        System.out.println("Listener Received : " + data);
        allocationService.splitJsonMsg(data);
    }
}
