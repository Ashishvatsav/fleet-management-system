package com.fleet.management.controller;

import com.fleet.management.entity.MaintenanceRecord;
import com.fleet.management.entity.MaintenanceSchedule;
import com.fleet.management.service.MaintenanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    // CREATE SCHEDULE
    @PostMapping("/schedule")
    public MaintenanceSchedule createSchedule(@RequestParam Long vehicleId,
                                              @RequestBody MaintenanceSchedule schedule) {
        return maintenanceService.createSchedule(vehicleId, schedule);
    }

    // GET SCHEDULES
    @GetMapping("/schedule/{vehicleId}")
    public List<MaintenanceSchedule> getSchedules(@PathVariable Long vehicleId) {
        return maintenanceService.getSchedules(vehicleId);
    }

    // ADD RECORD
    @PostMapping("/record")
    public MaintenanceRecord addRecord(@RequestParam Long vehicleId,
                                       @RequestParam Long mechanicId,
                                       @RequestBody MaintenanceRecord record) {
        return maintenanceService.addRecord(vehicleId, mechanicId, record);
    }

    // GET RECORDS
    @GetMapping("/record/{vehicleId}")
    public List<MaintenanceRecord> getRecords(@PathVariable Long vehicleId) {
        return maintenanceService.getRecords(vehicleId);
    }
}
