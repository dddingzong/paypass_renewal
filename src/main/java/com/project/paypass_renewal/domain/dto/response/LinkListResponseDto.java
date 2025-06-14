package com.project.paypass_renewal.domain.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkListResponseDto {
    private String userNumber;
    private String name;
    private String homeAddress;

    public LinkListResponseDto(String userNumber, String name, String homeAddress) {
        this.userNumber = userNumber;
        this.name = name;
        this.homeAddress = homeAddress;
    }
}
