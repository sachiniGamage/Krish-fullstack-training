package com.example.schedule;

import com.example.inverontoryservice.model.Allocation;
import com.example.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  KafkaListener {

    @Autowired
    private ScheduleService scheduleService;

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "AllocationComplete",
            groupId = "groupId3",
            containerFactory = "allocationListener"
    )
    void listener(Allocation data) {
        System.out.println("Listener Received : " + data);
        scheduleService.getAllocation(data);
    }
}
