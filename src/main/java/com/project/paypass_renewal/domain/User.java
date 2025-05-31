package com.project.paypass_renewal.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String mainId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime birth;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String homeAddress;

    private String centerAddress;

    @Column(nullable = false)
    private String linkCode;

    @Column(nullable = false)
    ServiceCode serviceCode;
}
