package com.example.inverontoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.order.model","com.example.inverontoryservice.model"})

public class InverontoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InverontoryServiceApplication.class, args);
    }

}
