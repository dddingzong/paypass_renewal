package com.project.paypass_renewal.domain;

import jakarta.persistence.*;

@Entity
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
