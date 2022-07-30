package com.example.order.service;

import com.example.order.model.Orders;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Orders saveOrder(Orders order) {
        orderRepository.save(order);
        return order;
    }

    public List<Orders> getAllOrders() {
        List<Orders> orderList = orderRepository.findAll();
        return orderList;
    }

    public Orders getOrderById(int id) {
        Optional<Orders> order = orderRepository.findById(id);
        if(order.isPresent()){
            return order.get();
        }else{
            return new Orders();
        }
    }
}
