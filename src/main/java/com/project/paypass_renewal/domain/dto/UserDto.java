package com.project.paypass_renewal.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.paypass_renewal.domain.ServiceCode;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "이메일은 필수 값입니다.")
    @NotEmpty(message = "이메일은 빈칸으로 작성할 수 없습니다")
    @Email(message = "이메일 형식을 지켜주십시오")
    private String mainId;

    @NotNull(message = "이름은 필수 값입니다.")
    @NotEmpty(message = "이름은 빈칸으로 작성할 수 없습니다")
    @Pattern(regexp = "^[가-힣]{2,4}$", message = "이름은 한글로 2~4자 사이로 작성해주세요")
    private String name;

    // LocalDate 타입은 @Valid 사용 불가, 따로 예외처리 진행
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past(message = "생년월일은 과거 날짜여야 합니다")
    @NotNull(message = "생년월일은 필수 값입니다.")
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
