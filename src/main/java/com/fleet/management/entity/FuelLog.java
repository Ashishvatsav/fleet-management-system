package com.fleet.management.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "fuel_logs")
@NoArgsConstructor
@Getter
@Setter

public class FuelLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fuel_efficiency")
    private Double fuelEfficiency = 0.0;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    @Column(name = "fuel_amount_liters", nullable = false)
    private Double fuelAmountLiters;

    @Column(name = "fuel_cost", nullable = false)
    private Double fuelCost;

    @Column(name = "odometer_at_refill", nullable = false)
    private Double odometerAtRefill;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "fuel_type", nullable = false, columnDefinition = "VARCHAR(50)")
    private FuelType fuelType;

    @Column(name = "refill_date", nullable = false)
    private LocalDateTime refillDate;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @PrePersist
    public void prePersist() {
        if (this.refillDate == null) {
            this.refillDate = LocalDateTime.now();
        }
    }

    public String getReceiptUrl() {
        return receiptUrl;
    }

    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }
}