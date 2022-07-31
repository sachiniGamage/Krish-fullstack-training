package com.example.inverontoryservice;

import org.springframework.stereotype.Component;

@Component
public class KafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "NewOrder",
            groupId = "groupId2"
    )
    public void listener(String data) {
        System.out.println("Listener Received : " + data);
    }
}
