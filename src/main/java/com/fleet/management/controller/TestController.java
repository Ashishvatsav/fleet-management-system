package com.fleet.management.controller;

import com.fleet.management.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private final VehicleRepository vehicleRepository;

    public TestController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public String testDB() {
        long count = vehicleRepository.count();
        return "DB Connected. Vehicle count = " + count;
    }
}