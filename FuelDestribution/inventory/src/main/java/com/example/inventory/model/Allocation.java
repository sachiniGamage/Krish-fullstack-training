package com.example.inventory.model;

import com.example.order.model.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Allocation")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int allocationId;

    private double allocateFuel;
    private FuelType fuelType;
    @Column(columnDefinition ="TINYINT default 1")
    private boolean allocated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_allocation",
            joinColumns = {@JoinColumn(name = "AllocationId", referencedColumnName = "allocationId")},
    inverseJoinColumns = {
            @JoinColumn(name = "orderId", referencedColumnName = "orderId"),
            @JoinColumn(name = "capacity", referencedColumnName = "capacity")})
    private List<Orders> orderList;
}
