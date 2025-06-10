package com.project.paypass_renewal.controller;


import com.project.paypass_renewal.domain.dto.UserLocationRequestDto;
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

    @PostMapping("/user/getUserLocation")
    public ResponseEntity<String> saveNewUser(@RequestBody UserLocationRequestDto userLocationRequestDto){

        userLocationService.saveUserLocation(userLocationRequestDto);

        return ResponseEntity.ok("success");
    }





}
