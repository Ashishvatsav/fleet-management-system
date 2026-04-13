package com.fleet.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance_schedules")
public class MaintenanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    
    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    
    private Integer serviceIntervalKM = 5000;

    private Double lastServiceKM;

    private Double nextServiceDueKM;

    
    @Enumerated(EnumType.STRING)
    private ScheduleStatus status;


    public Long getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Integer getServiceIntervalKM() {
        return serviceIntervalKM;
    }

    public void setServiceIntervalKM(Integer serviceIntervalKM) {
        this.serviceIntervalKM = serviceIntervalKM;
    }

    public Double getLastServiceKM() {
        return lastServiceKM;
    }

    public void setLastServiceKM(Double lastServiceKM) {
        this.lastServiceKM = lastServiceKM;
    }

    public Double getNextServiceDueKM() {
        return nextServiceDueKM;
    }

    public void setNextServiceDueKM(Double nextServiceDueKM) {
        this.nextServiceDueKM = nextServiceDueKM;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }
}