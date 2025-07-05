package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.UserLocation;
import com.project.paypass_renewal.domain.dto.request.NumberRequestDto;
import com.project.paypass_renewal.domain.dto.request.UserLocationRequestDto;
import com.project.paypass_renewal.domain.dto.response.UserLocationResponseDto;
import com.project.paypass_renewal.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLocationService {

    private final UserLocationRepository userLocationRepository;

    public UserLocation saveUserLocation(UserLocationRequestDto userLocationDto){

        UserLocation userLocation = toEntity(userLocationDto);

        userLocationRepository.save(userLocation);

        return userLocation;
    }

    private UserLocation toEntity(UserLocationRequestDto userLocationDto){

        String number = userLocationDto.getNumber();
        String latitude = userLocationDto.getLatitude();
        String longitude = userLocationDto.getLongitude();

        return new UserLocation(number, new BigDecimal(latitude), new BigDecimal(longitude));
    }


    public UserLocationResponseDto findRecentLocationByNumber(NumberRequestDto numberRequestDto) {
        String number = numberRequestDto.getNumber();

        List<UserLocation> userLocations = userLocationRepository.findByNumberOrderBySavedTimeDesc(number);

        UserLocation userLocation = userLocations.get(0);
        BigDecimal latitude = userLocation.getLatitude();
        BigDecimal longitude = userLocation.getLongitude();

        return new UserLocationResponseDto(latitude, longitude);
    }
}
