package com.example.order.controller;

import com.example.order.MessageRequest;
import com.example.order.model.FuelType;
import com.example.order.model.Orders;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:3001", "http://localhost:4200" })
@RequestMapping("/orderService")
public class OrderController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private OrderService orderService;

    @CrossOrigin
    @PostMapping (path = "/save")
    public Orders saveOrder(@RequestBody Orders order){
        System.out.println("client request recieved");
        Orders order1 = orderService.saveOrder(order);
        kafkaTemplate.send("NewOrder", order.toString());
        return order1;
    }

    @GetMapping(path = "/all")
    public List<Orders> getAllOrders(){
        List<Orders> orderList = orderService.getAllOrders();
        return orderList;
    }

    @GetMapping(path = "/order-by-id", params = "id")
    public Orders getOrderById(@RequestParam(value = "id")int id){
        Orders orderList = orderService.getOrderById(id);
        return orderList;
    }

//    @PostMapping(path = "/message")
//    public void publish(@RequestBody MessageRequest  messageRequest){
//        kafkaTemplate.send("NewOrder", messageRequest.toString());
//    }

    @GetMapping(path = "/getOrderedFuel")
    public Orders getOrderedFuelCapacity(@RequestParam(value = "fuelType")FuelType fuelType){
        Orders orders = orderService.getOrderedFuelCapacity(fuelType);
        return orders;
    }

}
