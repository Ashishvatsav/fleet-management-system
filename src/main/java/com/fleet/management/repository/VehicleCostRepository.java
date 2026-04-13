package com.fleet.management.repository;

import com.fleet.management.entity.VehicleCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleCostRepository extends JpaRepository<VehicleCost, Long> {

    
    List<VehicleCost> findByVehicleId(Long vehicleId);
}
