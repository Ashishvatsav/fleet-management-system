package com.fleet.management.service;

import com.fleet.management.entity.*;
import com.fleet.management.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    private final MaintenanceScheduleRepository scheduleRepo;
    private final MaintenanceRecordRepository recordRepo;
    private final VehicleRepository vehicleRepo;
    private final UserRepository userRepo;

    public MaintenanceService(MaintenanceScheduleRepository scheduleRepo,
                              MaintenanceRecordRepository recordRepo,
                              VehicleRepository vehicleRepo,
                              UserRepository userRepo) {
        this.scheduleRepo = scheduleRepo;
        this.recordRepo = recordRepo;
        this.vehicleRepo = vehicleRepo;
        this.userRepo = userRepo;
    }

    
    public MaintenanceSchedule createSchedule(Long vehicleId, MaintenanceSchedule schedule) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        schedule.setVehicle(vehicle);

        
        if (schedule.getLastServiceKM() != null) {
            schedule.setNextServiceDueKM(
                schedule.getLastServiceKM() + schedule.getServiceIntervalKM()
            );
        }

        return scheduleRepo.save(schedule);
    }

    
    public List<MaintenanceSchedule> getSchedules(Long vehicleId) {
        return scheduleRepo.findByVehicleId(vehicleId);
    }

    
    public MaintenanceRecord addRecord(Long vehicleId, Long mechanicId, MaintenanceRecord record) {

        Vehicle vehicle = vehicleRepo.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        User mechanic = userRepo.findById(mechanicId)
                .orElseThrow(() -> new RuntimeException("Mechanic not found"));

        record.setVehicle(vehicle);
        record.setPerformedBy(mechanic);

        return recordRepo.save(record);
    }

    
    public List<MaintenanceRecord> getRecords(Long vehicleId) {
        return recordRepo.findByVehicleId(vehicleId);
    }
}
