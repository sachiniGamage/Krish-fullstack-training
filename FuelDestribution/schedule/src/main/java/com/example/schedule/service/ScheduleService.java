package com.example.schedule.service;

import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.order.model.FuelType;
import com.example.order.model.Orders;
import com.example.schedule.model.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class ScheduleService {

    @Autowired
    private KafkaTemplate<String, Schedule> kafkaTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void getAllocation(Allocation allocation){
        Schedule schedule = new Schedule();
        CurrentStatus scheduleState = allocation.getAllocated();
        if(scheduleState == CurrentStatus.ALLOCATED){
            scheduleState = CurrentStatus.scheduled;
            schedule.setRandomDay(createRandomDate());

        }else{
            schedule.setRandomDay(null);
            scheduleState = CurrentStatus.notScheduled;
        }
        schedule.setOrder(allocation.getOrder());
        schedule.setScheduled(scheduleState);
        scheduleRepository.save(schedule);
        kafkaTemplate.send("DeliveryScheduled", schedule);
    }

    public LocalDate createRandomDate(){

        long currentDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.now().plusDays(30).toEpochDay();

        long randomD = ThreadLocalRandom.current().nextLong(currentDay,maxDay);

        LocalDate day = LocalDate.ofEpochDay(randomD);

        return  day;
    }

}
