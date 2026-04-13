package com.fleet.management.service;

import com.fleet.management.entity.Vehicle;
import com.fleet.management.entity.VehicleDetailsDTO;
import com.fleet.management.entity.FuelLog;
import com.fleet.management.entity.IssueReport;
import com.fleet.management.repository.VehicleRepository;
import com.fleet.management.repository.FuelLogRepository;
import com.fleet.management.repository.IssueReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final FuelLogRepository fuelLogRepository;
    private final IssueReportRepository issueReportRepository;

    public VehicleService(VehicleRepository vehicleRepository,
                          FuelLogRepository fuelLogRepository,
                          IssueReportRepository issueReportRepository) {
        this.vehicleRepository = vehicleRepository;
        this.fuelLogRepository = fuelLogRepository;
        this.issueReportRepository = issueReportRepository;
    }

    
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    
    public Vehicle updateVehicle(Long id, Vehicle updatedVehicle) {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

        if (vehicle == null) return null;

        vehicle.setRegistrationNumber(updatedVehicle.getRegistrationNumber());
        vehicle.setVehicleType(updatedVehicle.getVehicleType());
        vehicle.setMake(updatedVehicle.getMake());
        vehicle.setModel(updatedVehicle.getModel());
        vehicle.setYearOfManufacture(updatedVehicle.getYearOfManufacture());
        vehicle.setFuelType(updatedVehicle.getFuelType());
        vehicle.setOdometerReading(updatedVehicle.getOdometerReading());
        vehicle.setStatus(updatedVehicle.getStatus());

        return vehicleRepository.save(vehicle);
    }

   
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    
    public VehicleDetailsDTO getVehicleDetails(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        List<FuelLog> fuelLogs = fuelLogRepository.findAll()
                .stream()
                .filter(f -> f.getVehicle().getId().equals(vehicleId))
                .toList();

        List<IssueReport> issues = issueReportRepository.findAll()
                .stream()
                .filter(i -> i.getVehicle().getId().equals(vehicleId))
                .toList();

        return new VehicleDetailsDTO(vehicle, fuelLogs, issues);
    }
}
