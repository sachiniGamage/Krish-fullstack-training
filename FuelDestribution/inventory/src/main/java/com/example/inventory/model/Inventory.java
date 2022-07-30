package com.example.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "inventory")
public class Inventory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
        private int inventoryId;

        private double capacity;
        private FuelType fuelType;
//        private int gasStationId;
//        private String location;

}
