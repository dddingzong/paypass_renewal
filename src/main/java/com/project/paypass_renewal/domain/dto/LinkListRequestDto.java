package com.project.paypass_renewal.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkListRequestDto {
    private String userNumber;
    private String name;
    private String homeAddress;

    public LinkListRequestDto(String userNumber, String name, String homeAddress) {
        this.userNumber = userNumber;
        this.name = name;
        this.homeAddress = homeAddress;
    }
}
