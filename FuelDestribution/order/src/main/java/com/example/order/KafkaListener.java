package com.example.order;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "OrderDispatched",
            groupId = "groupId5"
    )
    void listener(String data) {
        System.out.println("Listener Received : " + data);
    }
}
