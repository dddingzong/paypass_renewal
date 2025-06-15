package com.project.paypass_renewal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Getter
@Entity
@RequiredArgsConstructor
public class UserCareGeofence {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private BigDecimal homeLatitude;

    @Column(nullable = false)
    private BigDecimal homeLongitude;

    private BigDecimal centerLatitude;

    private BigDecimal centerLongitude;

}
