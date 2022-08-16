package com.example.dispatch.service;

import com.example.dispatch.repository.DispatchRepository;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.order.model.FuelType;
import com.example.order.model.Orders;
import com.example.schedule.model.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DispatchService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private DispatchRepository dispatchRepository;

    //update allocation table allocated column to distributed when dispatch button clicked
    public void dispatchOrder() {
        Schedule schedule = new Schedule();
        CurrentStatus currentStatus = CurrentStatus.distributed;
        schedule.setScheduled(currentStatus);
        dispatchRepository.save(schedule);
        kafkaTemplate.send("OrderDispatched", schedule.toString());
    }

    //display all the scheduled orders
    @JsonIgnore
    public List<Schedule> getAllScheduledOrders() {
        CurrentStatus cs = CurrentStatus.scheduled;
        List<Schedule> scheduleList = dispatchRepository.findScheduleByScheduled(cs);
        return scheduleList;
    }

    public void dispatchById(int id) {
        CurrentStatus currentStatus = CurrentStatus.distributed;
        Schedule s = dispatchRepository.findScheduleByOrderGasStationId(id);
        s.setScheduled(currentStatus);
        dispatchRepository.save(s);
        kafkaTemplate.send("OrderDispatched", s.toString());

    }

    public void updateDispatch(int id) {
        CurrentStatus currentStatus = CurrentStatus.distributed;
        Schedule s = dispatchRepository.findScheduleByOrderGasStationId(id);
        s.setScheduled(currentStatus);
        dispatchRepository.save(s);
        kafkaTemplate.send("OrderDispatched", s.toString());
    }

    public Schedule getOrderById(int id) {
        Schedule schedule = dispatchRepository.findScheduleByOrderGasStationId(id);
        return schedule;
    }
}
