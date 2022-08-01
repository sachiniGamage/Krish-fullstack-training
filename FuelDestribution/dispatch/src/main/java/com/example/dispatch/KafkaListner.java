package com.example.dispatch;

import com.example.dispatch.service.DispatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListner {

    @Autowired
    private DispatchService dispatchService;

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "DeliveryScheduled",
            groupId = "groupId4"
    )
    void listener(String data) {
        System.out.println("Listener Received : " + data);
        dispatchService.convertJsonMsg(data);
    }

}
