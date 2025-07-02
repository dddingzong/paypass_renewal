package com.project.paypass_renewal.domain.data;

import jakarta.persistence.*;

@Entity
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long stationNumber;

    private double longitude;

    private double latitude;

    @Column(columnDefinition = "TEXT")
    private String busInfo;
}
