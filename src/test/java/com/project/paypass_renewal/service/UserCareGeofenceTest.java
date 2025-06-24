package com.project.paypass_renewal.service;

import com.project.paypass_renewal.repository.UserCareGeofenceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserCareGeofenceTest {

    @Mock
    private UserCareGeofenceRepository userCareGeofenceRepository;

    @InjectMocks
    private UserCareGeofenceService userCareGeofenceService;

    @Test
    @DisplayName("유저_지오펜스_저장_테스트")
    void saveUserGeofenceTest() {
        // given
        String zipcode = "12432";

        // when
        userCareGeofenceService.saveUserGeofence(zipcode);


        // then



    }



}
