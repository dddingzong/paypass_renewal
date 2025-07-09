package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.data.Station;
import com.project.paypass_renewal.domain.dto.response.StationListResponseDto;
import com.project.paypass_renewal.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @GetMapping("/station/getStationList")
    public ResponseEntity<List<StationListResponseDto>> getStationList() {
        List<StationListResponseDto> stationList = stationService.getStationList();
        return ResponseEntity.ok(stationList);
    }




}
