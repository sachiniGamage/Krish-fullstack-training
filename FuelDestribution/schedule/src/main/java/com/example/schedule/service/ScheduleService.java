package com.example.schedule.service;

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




    //todo: random date not pick a random day - fix it



    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public void saveScheduleDetails(Schedule schedule, Orders order, CurrentStatus currentStatus){
        CurrentStatus scheduleState;
        if(currentStatus == CurrentStatus.ALLOCATED){
            scheduleState = CurrentStatus.scheduled;
            schedule.setRandomDay(createRandomDate());

        }else{
            schedule.setRandomDay(null);
            scheduleState = CurrentStatus.notScheduled;
        }

        schedule.setOrder(order);
        schedule.setScheduled(scheduleState);
        scheduleRepository.save(schedule);
        kafkaTemplate.send("DeliveryScheduled", schedule.toString());
    }

    public LocalDate createRandomDate(){

        long currentDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.now().plusDays(30).toEpochDay();

        long randomD = ThreadLocalRandom.current().nextLong(currentDay,maxDay);

        LocalDate day = LocalDate.ofEpochDay(randomD);

        return  day;
    }

    public Orders order(int id, String name, String location, int gasStationId, double capacity, FuelType fuelType) {
        Orders orders = new Orders();
        orders.setOrderId(id);
        orders.setCpcOwnerName(name);
        orders.setLocation(location);
        orders.setGasStationId(gasStationId);
        orders.setFuelCapacity(capacity);
        orders.setFuelType(fuelType);

        return orders;
    }

    public void allocationMsgConvert(String msg){

        String[] split1 = msg.split("Allocation\\(");
        String part1 = split1[1];

        String[] split2 = part1.split("Orders\\(");
        String part2 = split2[1];

        String[] split3 = part2.split("\\), ");
        String part3 = split3[0]; // orderlist
        String part4 = split3[1]; // allocated

        String[] split5 = part3.split(", ");
        List<String> list1 = new ArrayList<>();
        for (String string : split5) {
            list1.add(string);
        }
        System.out.println(list1);

        List<String> variables = new ArrayList<>();
        List<String> values1 = new ArrayList<>();
        for (String string2 : list1) {
            String[] split6 = string2.split("=");
            variables.add(split6[0]);
            values1.add(split6[1]);
        }

        String[] split7 = part4.split("=");
        String part5 = split7[0];// variable
        String part6 = split7[1];

        String[] split8 = part6.split("\\)");
        String part7 = split8[0];//value

        variables.add(part5);
        values1.add(part7);

        System.out.println(variables);
        System.out.println(values1);

        int orderId = Integer.parseInt(values1.get(0));
        String name = values1.get(1);
        String location = values1.get(2);
        int gasStationId = Integer.parseInt(values1.get(3));
        double capacity = Double.parseDouble(values1.get(4));
        FuelType fuelType = FuelType.valueOf(values1.get(5));

        try{
            CurrentStatus cs = CurrentStatus.valueOf(values1.get(6));
            Schedule schedule = new Schedule();
            saveScheduleDetails(schedule,order(orderId,name,location,gasStationId,capacity,fuelType),cs);
        }catch(Exception e){
            e.printStackTrace();
        }



    }
}
