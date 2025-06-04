package com.project.paypass_renewal.domain;

import jakarta.persistence.*;

@Entity
public class UserLocation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mainId;

    @Column(nullable = false)
    private Long latitude;

    @Column(nullable = false)
    private Long longitude;
}
