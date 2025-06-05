package com.project.paypass_renewal.support;

import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.dto.UserDto;

import java.time.LocalDate;

public class UserDtoTestUtil {

    public static UserDto createDummyUserDto() {
        return new UserDto("dummy@gmail.com",
                "더미유저",
                LocalDate.of(2000, 1, 1),
                "01012345678",
                "12345",
                "67890",
                ServiceCode.PROTECT_SERVICE);
    }
}
