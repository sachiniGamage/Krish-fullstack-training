package com.example.dispatch.controller;

import com.example.dispatch.service.DispatchService;
import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.schedule.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispatching")
public class DispatchController {

    @Autowired
    private DispatchService dispatchService;

    //only update allocated column to distributed
    @PutMapping(path = "/dispatch")
    public void dispatchOrder(){
        dispatchService.dispatchOrder();
    }

    @GetMapping(path = "/all")
    public List<Schedule> getAllScheduledOrders(){
        List<Schedule> scheduleList = dispatchService.getAllScheduledOrders();
        return scheduleList;
    }
}
