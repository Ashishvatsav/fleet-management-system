package com.fleet.management.repository;

import com.fleet.management.entity.FuelLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelLogRepository extends JpaRepository<FuelLog, Long> {
    List<FuelLog> findByVehicleId(Long vehicleId);

    List<FuelLog> findAllByVehicleIdOrderByRefillDateAsc(Long vehicleId);
}
