package com.project.paypass_renewal.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mainId;

    @Column(nullable = false)
    private Long homeLatitude;

    @Column(nullable = false)
    private Long homeLongitude;

    Long centerLatitude;

    Long centerLongitude;
}
