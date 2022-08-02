package com.example.order;

import org.springframework.stereotype.Component;

@Component
public class ScheduleOrderKafkaListener {
    @org.springframework.kafka.annotation.KafkaListener(
            topics = "DeliveryScheduled",
            groupId = "groupId7"
    )
    void listener(String data) {
        System.out.println("Listener Received : " + data);
    }
}
