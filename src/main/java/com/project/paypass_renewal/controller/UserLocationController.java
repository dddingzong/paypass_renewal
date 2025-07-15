package com.project.paypass_renewal.controller;


import com.project.paypass_renewal.domain.dto.request.NumberRequestDto;
import com.project.paypass_renewal.domain.dto.request.UserLocationRequestDto;
import com.project.paypass_renewal.domain.dto.response.UserLocationResponseDto;
import com.project.paypass_renewal.service.UserLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLocationController {

    private final  UserLocationService userLocationService;

    @PostMapping("/user/saveUserLocation")
    public ResponseEntity<String> saveNewUser(@RequestBody UserLocationRequestDto userLocationRequestDto){

        userLocationService.saveUserLocation(userLocationRequestDto);

        return ResponseEntity.ok("saveSuccess");
    }

    @PostMapping("/user/getRecentUserLocation")
    public ResponseEntity<UserLocationResponseDto> getRecentUserLocation(@RequestBody NumberRequestDto numberRequestDto) {
        UserLocationResponseDto userLocationResponseDto = userLocationService.findRecentLocationByNumber(numberRequestDto);

        return ResponseEntity.ok(userLocationResponseDto);
    }





}
