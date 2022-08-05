package com.example.dispatch.service;

import com.example.dispatch.repository.DispatchRepository;
import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.model.CurrentStatus;
import com.example.order.model.FuelType;
import com.example.order.model.Orders;
import com.example.schedule.model.Schedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CurrencyEditor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public void convertJsonMsg(String msg){
        String[] split1 = msg.split("Orders\\(");
        String part1 = split1[1];

        String[] split2 = part1.split("\\), ");
        String part2 = split2[0];
        String part3 = split2[1];

        String[] split3 = part2.split(", ");
        List<String> list1 = new ArrayList<>();
        for (String string : split3) {
            list1.add(string);
        }

        String[] split4 = part3.split("\\)");
        String part4 = split4[0];

        String[] split5 = part4.split(", ");
        String part5 = split5[0];// scheduled=scheduled
        String part6 = split5[1];// randomDay=2022-08-26

        // scheduled=scheduled
        String[] split6 = part5.split("=");
        String part7 = split6[0]; // variable
        String part8 = split6[1]; // value

        // randomDay=2022-08-26
        String[] split7 = part6.split("=");
        String part9 = split7[0]; // variable
        String part10 = split7[1]; // value

        List<String> variables = new ArrayList<>();
        List<String> values = new ArrayList<>();
        for (String string2 : list1) {
            String[] split8 = string2.split("=");
            variables.add(split8[0]);
            values.add(split8[1]);
        }

        variables.add(part7);
        values.add(part8);

        variables.add(part9);
        values.add(part10);

        System.out.println(variables);
        System.out.println(values);

        int OrderId = Integer.parseInt(values.get(0));
        String name = values.get(1);
        String location = values.get(2);
        int gasStationId = Integer.parseInt(values.get(3));
        double capacity = Double.parseDouble(values.get(4));
        FuelType fuelType = FuelType.valueOf(values.get(5));
        CurrentStatus cs = CurrentStatus.valueOf(values.get(6));

//        String[] s1 = part10.split("-");
//        int year = Integer.parseInt(s1[0]);
//
//        String p =  s1[1];
//        String[] s2 = p.split("-");
//        int month  = Integer.parseInt(s2[0]) ;
//        int day1 = Integer.parseInt(s2[1]);
//
//        LocalDate day = LocalDate.of(year,month,day1);
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
}
