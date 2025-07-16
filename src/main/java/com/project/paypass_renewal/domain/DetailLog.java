package com.project.paypass_renewal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
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

    public DetailLog(Long log_id, String number, LocalDateTime fenceInTime, LocalDateTime fenceOutTime, Long stationNumber) {
        this.log_id = log_id;
        this.number = number;
        this.fenceInTime = fenceInTime;
        this.fenceOutTime = fenceOutTime;
        this.stationNumber = stationNumber;
    }

    // 테스트용 생성자
    public DetailLog(Long id, Long log_id, String number, LocalDateTime fenceInTime, LocalDateTime fenceOutTime, Long stationNumber) {
        this.id = id;
        this.log_id = log_id;
        this.number = number;
        this.fenceInTime = fenceInTime;
        this.fenceOutTime = fenceOutTime;
        this.stationNumber = stationNumber;
    }
}
