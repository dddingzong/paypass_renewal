package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.UserLocation;
import com.project.paypass_renewal.domain.dto.UserLocationRequestDto;
import com.project.paypass_renewal.repository.UserLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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


}
