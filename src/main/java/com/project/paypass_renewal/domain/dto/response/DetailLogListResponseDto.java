package com.project.paypass_renewal.domain.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DetailLogListResponseDto {
    private Long id;
    private LocalDateTime fenceInTime;
    private LocalDateTime fenceOutTime;
    private Long stationNumber;
    private String stationName;

    public DetailLogListResponseDto(Long id, LocalDateTime fenceInTime, LocalDateTime fenceOutTime, Long stationNumber, String stationName) {
        this.id = id;
        this.fenceInTime = fenceInTime;
        this.fenceOutTime = fenceOutTime;
        this.stationNumber = stationNumber;
        this.stationName = stationName;
    }
}


