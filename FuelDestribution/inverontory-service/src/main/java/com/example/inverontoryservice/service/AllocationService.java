package com.example.inverontoryservice.service;

import com.example.inverontoryservice.KafkaListener;
import com.example.inverontoryservice.model.Allocation;
import com.example.inverontoryservice.repository.AllocationRepository;
import com.example.order.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepository;

    public Allocation allocate(Allocation allocation) {
        allocationRepository.save(allocation);
        return allocation;
    }

//    public void convertMsgToJson(String msg){
//        Gson g=
//        Orders orders =
//    }

    public void splitJsonMsg(String msg){
        
    }
}
