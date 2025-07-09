package com.project.paypass_renewal.domain.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class StationListResponseDto {
    private String name;
    private Long stationNumber;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public StationListResponseDto(String name, Long stationNumber, BigDecimal longitude, BigDecimal latitude) {
        this.name = name;
        this.stationNumber = stationNumber;
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
