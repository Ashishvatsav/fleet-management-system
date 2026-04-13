package com.fleet.management.entity;

import java.util.List;

public class VehicleDetailsDTO {

    private Vehicle vehicle;
    private List<FuelLog> fuelLogs;
    private List<IssueReport> issues;

    public VehicleDetailsDTO(Vehicle vehicle,
                             List<FuelLog> fuelLogs,
                             List<IssueReport> issues) {
        this.vehicle = vehicle;
        this.fuelLogs = fuelLogs;
        this.issues = issues;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public List<FuelLog> getFuelLogs() {
        return fuelLogs;
    }

    public List<IssueReport> getIssues() {
        return issues;
    }
}
