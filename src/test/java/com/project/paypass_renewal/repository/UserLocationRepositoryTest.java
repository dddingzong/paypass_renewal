package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.UserLocation;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserLocationRepositoryTest {

    @Autowired
    UserLocationRepository userLocationRepository;

    @Test
    @DisplayName("실시간_위치_저장")
    void saveUserLocationTest(){
        // given
        UserLocation userLocation = new UserLocation("test@gmail.com", BigDecimal.valueOf(37.6616521), BigDecimal.valueOf(127.0561246));

        // when
        UserLocation savedUserLocation = userLocationRepository.save(userLocation);

        // then
        Long id = savedUserLocation.getId();

        assertThat(id).isEqualTo(userLocation.getId());
    }
}
