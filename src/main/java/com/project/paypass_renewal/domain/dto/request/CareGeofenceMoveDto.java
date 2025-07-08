package com.project.paypass_renewal.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CareGeofenceMoveDto {
    private String name;
    private LocalDateTime time;

    public CareGeofenceMoveDto(String name, LocalDateTime time) {
        this.name = name;
        this.time = time;
    }
}
