package com.fleet.management.entity;

import com.fleet.management.entity.FuelType;
import com.fleet.management.entity.VehicleStatus;
import com.fleet.management.entity.VehicleType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_number", unique = true, nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "vehicle_type", columnDefinition = "VARCHAR(50)")
    private VehicleType vehicleType;

    @Column(name = "make", nullable = true)
    private String make;
    
    @Column(name = "model", nullable = true)
    private String model;

    @Column(name = "year_of_manufacture", nullable = true)
    private Integer yearOfManufacture;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "fuel_type", columnDefinition = "VARCHAR(50)")
    private FuelType fuelType;

    @Column(name = "odometer_reading", nullable = true)
    private Double odometerReading;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "status", columnDefinition = "VARCHAR(50)")
    private VehicleStatus status;
}
