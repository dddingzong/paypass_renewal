package com.project.paypass_renewal.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class DetailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long log_id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private LocalDateTime fenceInTime;

    private LocalDateTime fenceOutTime;

    @Column(nullable = false)
    private Long stationNumber;

}
