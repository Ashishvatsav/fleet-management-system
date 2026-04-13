package com.fleet.management.dto;

import com.fleet.management.entity.FuelType;
import com.fleet.management.entity.VehicleStatus;
import com.fleet.management.entity.VehicleType;

public class VehicleDTO {
    public String registrationNumber;
    public VehicleType vehicleType;
    public String make;
    public String model;
    public Integer yearOfManufacture;
    public FuelType fuelType;
    public Double odometerReading;
    public VehicleStatus status;
}
