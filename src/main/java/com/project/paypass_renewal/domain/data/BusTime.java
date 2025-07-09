package com.project.paypass_renewal.domain.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BusTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String routeId;

    @Column(nullable = false)
    private int sequence;

    @Column(nullable = false)
    private String arrivalTime;
}
