package com.example.order;

import org.springframework.stereotype.Component;

@Component
public class OrderFailKafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "cannotAllocate",
            groupId = "groupId6"
    )
    void listener(String data) {
        System.out.println("Listener Received : " + data);
    }
}
