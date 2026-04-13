package com.fleet.management.service;

import com.fleet.management.entity.FuelLog;
import com.fleet.management.entity.User;
import com.fleet.management.entity.Vehicle;
import com.fleet.management.repository.FuelLogRepository;
import com.fleet.management.repository.UserRepository;
import com.fleet.management.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelLogService {

    private final FuelLogRepository fuelLogRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;

    public FuelLogService(FuelLogRepository fuelLogRepository,
                          VehicleRepository vehicleRepository,
                          UserRepository userRepository) {
        this.fuelLogRepository = fuelLogRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    
    public FuelLog addFuelLog(Long vehicleId, Long driverId, FuelLog fuelLog) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        User driver = userRepository.findById(driverId).orElse(null);

        if (vehicle == null || driver == null) return null;

        fuelLog.setVehicle(vehicle);
        fuelLog.setDriver(driver);

        
        List<FuelLog> logs = fuelLogRepository.findAll();
        FuelLog lastLog = null;

        for (FuelLog log : logs) {
            if (log.getVehicle() != null && log.getVehicle().getId().equals(vehicleId)) {
                if (lastLog == null || log.getOdometerAtRefill() > lastLog.getOdometerAtRefill()) {
                    lastLog = log;
                }
            }
        }

        
        if (lastLog != null && fuelLog.getOdometerAtRefill() <= lastLog.getOdometerAtRefill()) {
            throw new RuntimeException("Odometer must be increasing");
        }

        
        if (lastLog != null && lastLog.getOdometerAtRefill() != null && fuelLog.getOdometerAtRefill() != null) {
            double distance = fuelLog.getOdometerAtRefill() - lastLog.getOdometerAtRefill();

            if (fuelLog.getFuelAmountLiters() != null && fuelLog.getFuelAmountLiters() != 0) {
                double efficiency = distance / fuelLog.getFuelAmountLiters();
                fuelLog.setFuelEfficiency(efficiency);
            }
        } else {
            fuelLog.setFuelEfficiency(0.0);
        }

        return fuelLogRepository.save(fuelLog);
    }

    
    public List<FuelLog> getAllFuelLogs() {
        return fuelLogRepository.findAll();
    }

    
    public FuelLog getFuelLogById(Long id) {
        return fuelLogRepository.findById(id).orElse(null);
    }


    public void deleteFuelLog(Long id) {
        fuelLogRepository.deleteById(id);
    }

    
    public List<FuelLog> getByVehicle(Long vehicleId) {
        return fuelLogRepository.findByVehicleId(vehicleId);
    }

    
    public Double getAverageEfficiency(Long vehicleId) {

        List<FuelLog> logs = fuelLogRepository.findAll()
                .stream()
                .filter(f -> f.getVehicle().getId().equals(vehicleId))
                .toList();

        if (logs.isEmpty()) return 0.0;

        double total = 0;
        int count = 0;

        for (FuelLog log : logs) {
            if (log.getFuelEfficiency() != null && log.getFuelEfficiency() > 0) {
                total += log.getFuelEfficiency();
                count++;
            }
        }

        return count == 0 ? 0.0 : total / count;
    }
}
