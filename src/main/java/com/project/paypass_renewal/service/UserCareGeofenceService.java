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

        String homeAddress = userRequestDto.getHomeAddress();
        String centerAddress = userRequestDto.getCenterAddress();

        Map<String, BigDecimal> homeLatLog = ZipCodeToLatLogUtils.getLatLogFromZipCode(homeAddress);
        Map<String, BigDecimal> centerLatLog = ZipCodeToLatLogUtils.getLatLogFromZipCode(centerAddress);

        log.info("geofence 생성을 완료했습니다.");

        UserCareGeofence userCareGeofence = new UserCareGeofence(
                userRequestDto.getNumber(),
                homeLatLog.get("latitude"),
                homeLatLog.get("longitude"),
                centerLatLog.get("latitude"),
                centerLatLog.get("longitude")
        );

        userCareGeofenceRepository.save(userCareGeofence);

        return userCareGeofence;
    }
}
