package com.example.schedule;

import com.example.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @Autowired
    private ScheduleService scheduleService;

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "AllocationComplete",
            groupId = "groupId3"
    )
    void listener(String data) {
        System.out.println("Listener Received : " + data);
        scheduleService.allocationMsgConvert(data);
    }
}
