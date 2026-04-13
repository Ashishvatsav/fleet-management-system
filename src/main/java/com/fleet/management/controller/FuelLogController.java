package com.fleet.management.controller;

import com.fleet.management.entity.FuelLog;
import com.fleet.management.service.FuelLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel-logs")
public class FuelLogController {

    private final FuelLogService fuelLogService;

    public FuelLogController(FuelLogService fuelLogService) {
        this.fuelLogService = fuelLogService;
    }


    @PostMapping
    public FuelLog addFuelLog(@RequestParam Long vehicleId,
                             @RequestParam Long driverId,
                             @RequestBody FuelLog fuelLog) {
        return fuelLogService.addFuelLog(vehicleId, driverId, fuelLog);
    }


    @GetMapping
    public List<FuelLog> getAllFuelLogs() {
        return fuelLogService.getAllFuelLogs();
    }


    @GetMapping("/id/{id}")
    public FuelLog getFuelLogById(@PathVariable Long id) {
        return fuelLogService.getFuelLogById(id);
    }


    @GetMapping("/vehicle/{vehicleId}")
    public List<FuelLog> getByVehicle(@PathVariable Long vehicleId) {
        return fuelLogService.getByVehicle(vehicleId);
    }


    @GetMapping("/efficiency/{vehicleId}")
    public Double getEfficiency(@PathVariable Long vehicleId) {
        return fuelLogService.getAverageEfficiency(vehicleId);
    }


    @DeleteMapping("/{id}")
    public String deleteFuelLog(@PathVariable Long id) {
        fuelLogService.deleteFuelLog(id);
        return "Fuel log deleted successfully";
    }
}