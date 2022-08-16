package com.example.dispatch;

import com.example.dispatch.service.DispatchService;
import com.example.schedule.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListner {

    @Autowired
    private DispatchService dispatchService;

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "DeliveryScheduled",
            groupId = "groupId4",
            containerFactory = "dispatchListener"
    )
    void listener(Schedule data) {
        System.out.println("Listener Received : " + data);
    }

}
