package com.project.paypass_renewal.support;

import com.project.paypass_renewal.domain.ServiceCode;
import com.project.paypass_renewal.domain.User;

import java.time.LocalDate;

public class UserTestUtils {

    public static User createDummyUser() {
        return new User("dummy@gmail.com",
                "더미유저",
                LocalDate.of(2000, 1, 1),
                "01012345678",
                "12345",
                "67890",
                "123456",
                ServiceCode.PROTECT_SERVICE);
    }

}
