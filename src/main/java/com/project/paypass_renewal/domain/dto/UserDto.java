package com.project.paypass_renewal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.paypass_renewal.domain.ServiceCode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "이메일은 필수 값입니다.")
    private String mainId;

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String number;

    private String homeAddress;

    private String centerAddress;

    private ServiceCode serviceCode;

    public UserDto(String mainId, String name, LocalDate birth, String number, String homeAddress, String centerAddress, ServiceCode serviceCode) {
        this.mainId = mainId;
        this.name = name;
        this.birth = birth;
        this.number = number;
        this.homeAddress = homeAddress;
        this.centerAddress = centerAddress;
        this.serviceCode = serviceCode;
    }
}
