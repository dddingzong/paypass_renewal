package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.UserCareGeofence;
import com.project.paypass_renewal.domain.dto.request.UserRequestDto;
import com.project.paypass_renewal.util.ZipCodeToLatLogUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class UserCareGeofenceService {

    public UserCareGeofence saveUserGeofence(UserRequestDto userRequestDto) {

        String homeAddress = userRequestDto.getHomeAddress();
        String centerAddress = userRequestDto.getCenterAddress();

        Map<String, BigDecimal> homeLatLog = ZipCodeToLatLogUtils.getLatLogFromZipCode(homeAddress);
        Map<String, BigDecimal> centerLatLog = ZipCodeToLatLogUtils.getLatLogFromZipCode(centerAddress);

        return new UserCareGeofence(
                userRequestDto.getNumber(),
                homeLatLog.get("latitude"),
                homeLatLog.get("longitude"),
                centerLatLog.get("latitude"),
                centerLatLog.get("longitude")
        );
    }
}
