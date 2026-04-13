package com.fleet.management.controller;

import com.fleet.management.entity.VehicleCost;
import com.fleet.management.service.VehicleCostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/costs")
public class VehicleCostController {

    private final VehicleCostService vehicleCostService;

    public VehicleCostController(VehicleCostService vehicleCostService) {
        this.vehicleCostService = vehicleCostService;
    }

    // RECORD COST
    @PostMapping("/record")
    public VehicleCost recordCost(@RequestParam Long vehicleId,
                                 @RequestBody VehicleCost cost) {
        return vehicleCostService.recordCost(vehicleId, cost);
    }

    // GET COSTS BY VEHICLE
    @GetMapping("/vehicle/{vehicleId}")
    public List<VehicleCost> getCostsByVehicle(@PathVariable Long vehicleId) {
        return vehicleCostService.getCostsByVehicle(vehicleId);
    }

    // GET COST SUMMARY (AGGREGATE)
    @GetMapping("/summary")
    public Map<String, Double> getCostSummary() {
        return vehicleCostService.getCostSummary();
    }
}
