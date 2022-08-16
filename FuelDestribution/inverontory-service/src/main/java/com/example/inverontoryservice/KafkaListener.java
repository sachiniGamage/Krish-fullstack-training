package com.example.inverontoryservice;

import com.example.inverontoryservice.service.AllocationService;
import com.example.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @Autowired
    private AllocationService allocationService;

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "NewOrder",
            groupId = "groupId2",
            containerFactory = "orderListener"
    )
    void listener(Orders data) {
        System.out.println("Listener Received : " + data);
        allocationService.getOrderData(data);
    }

}
