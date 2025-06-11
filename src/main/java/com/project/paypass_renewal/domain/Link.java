package com.project.paypass_renewal.domain;

import jakarta.persistence.*;

@Entity
public class Link {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String supportNumber;

    @Column(nullable = false)
    private String userNumber;
}
