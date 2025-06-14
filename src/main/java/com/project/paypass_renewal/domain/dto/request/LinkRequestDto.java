package com.project.paypass_renewal.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkRequestDto {
    private String supporterNumber;
    private String userNumber;

    public LinkRequestDto(String supporterNumber, String userNumber) {
        this.supporterNumber = supporterNumber;
        this.userNumber = userNumber;
    }
}
