package com.project.paypass_renewal.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CenterAddressRequestDto {
    private String number;
    private String centerAddress;
    private String centerStreetAddress;

    public CenterAddressRequestDto(String number, String centerAddress, String centerStreetAddress) {
        this.number = number;
        this.centerAddress = centerAddress;
        this.centerStreetAddress = centerStreetAddress;
    }
}
