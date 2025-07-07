package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.UserCareGeofence;
import com.project.paypass_renewal.domain.dto.request.UserRequestDto;
import com.project.paypass_renewal.repository.UserCareGeofenceRepository;
import com.project.paypass_renewal.util.ZipCodeToLatLogUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCareGeofenceService {

    private final UserCareGeofenceRepository userCareGeofenceRepository;

    @Transactional
    public UserCareGeofence saveUserGeofence(UserRequestDto userRequestDto) {
        log.info("유저 저장으로 인해 집과 센터에 geofence를 생성합니다.");

        BigDecimal centerLatitude = null;
        BigDecimal centerLongitude = null;

        String homeAddress = userRequestDto.getHomeAddress();
        String centerAddress = userRequestDto.getCenterAddress();

        System.out.println("userRequestDto = " + userRequestDto);

        Map<String, BigDecimal> homeLatLog = ZipCodeToLatLogUtils.getLatLogFromZipCode(homeAddress);

        if (centerAddress != null) {
            Map<String, BigDecimal> centerLatLog = ZipCodeToLatLogUtils.getLatLogFromZipCode(centerAddress);
            centerLatitude = centerLatLog.get("latitude");
            centerLongitude = centerLatLog.get("longitude");
        }

        log.info("geofence 생성을 완료했습니다.");

        UserCareGeofence userCareGeofence = new UserCareGeofence(
                userRequestDto.getNumber(),
                homeLatLog.get("latitude"),
                homeLatLog.get("longitude"),
                centerLatitude,
                centerLongitude
        );

        userCareGeofenceRepository.save(userCareGeofence);

        return userCareGeofence;
    }
}
