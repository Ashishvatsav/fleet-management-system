package com.fleet.management.service;

import com.fleet.management.entity.Vehicle;
import com.fleet.management.entity.VehicleCost;
import com.fleet.management.repository.VehicleCostRepository;
import com.fleet.management.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehicleCostService {

    private final VehicleCostRepository vehicleCostRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleCostService(VehicleCostRepository vehicleCostRepository,
                              VehicleRepository vehicleRepository) {
        this.vehicleCostRepository = vehicleCostRepository;
        this.vehicleRepository = vehicleRepository;
    }

   
    public VehicleCost recordCost(Long vehicleId, VehicleCost cost) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        cost.setVehicle(vehicle);

        return vehicleCostRepository.save(cost);
    }

    
    public List<VehicleCost> getCostsByVehicle(Long vehicleId) {
        return vehicleCostRepository.findByVehicleId(vehicleId);
    }

    
    public Map<String, Double> getCostSummary() {

        List<VehicleCost> costs = vehicleCostRepository.findAll();

        Map<String, Double> summary = new HashMap<>();

        for (VehicleCost cost : costs) {
            String type = cost.getCostType().name();
            summary.put(type, summary.getOrDefault(type, 0.0) + cost.getAmount());
        }

        return summary;
    }
}
