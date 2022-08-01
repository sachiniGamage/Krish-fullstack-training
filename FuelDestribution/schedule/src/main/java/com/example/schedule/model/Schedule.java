package com.example.schedule.model;

import com.example.inverontoryservice.model.CurrentStatus;
import com.example.order.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduleId;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "allocationId", referencedColumnName = "allocationId")
//    private Allocation allocation;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Orders order;

    private CurrentStatus scheduled;

    private LocalDate randomDay;

}
