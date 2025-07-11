package com.project.paypass_renewal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class PaypassGeofence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private LocalDateTime fenceInTime;

    private LocalDateTime fenceOutTime;

    @Column(nullable = false)
    private Long stationNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String busInfo;

    public PaypassGeofence(String number, Long stationNumber, String busInfo) {
        this.number = number;
        this.fenceInTime = LocalDateTime.now();
        this.fenceOutTime = null;
        this.stationNumber = stationNumber;
        this.busInfo = busInfo;
    }

}
