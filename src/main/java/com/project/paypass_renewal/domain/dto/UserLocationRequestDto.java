package com.project.paypass_renewal.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLocationRequestDto {

    private String mainId;
    private String latitude;
    private String longitude;

    public UserLocationRequestDto(String mainId, String latitude, String longitude) {
        this.mainId = mainId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
