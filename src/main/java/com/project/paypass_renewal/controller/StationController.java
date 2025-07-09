package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.data.Station;
import com.project.paypass_renewal.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @PostMapping("/station/getStationList")
    public ResponseEntity<List<Station>> getStationList() {
        List<Station> stationList = stationService.findAll();
        return ResponseEntity.ok(stationList);
    }




}
