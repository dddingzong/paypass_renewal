package com.project.paypass_renewal.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class UserAddress {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private BigDecimal homeLatitude;

    @Column(nullable = false)
    private BigDecimal homeLongitude;

    BigDecimal centerLatitude;

    BigDecimal centerLongitude;
}
