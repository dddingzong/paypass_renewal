package com.project.paypass_renewal.controller;


import com.project.paypass_renewal.domain.DetailLog;
import com.project.paypass_renewal.domain.data.Station;
import com.project.paypass_renewal.domain.dto.request.LogIdRequestDto;
import com.project.paypass_renewal.domain.dto.response.DetailLogListResponseDto;
import com.project.paypass_renewal.service.DetailLogService;
import com.project.paypass_renewal.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DetailLogController {

    private final DetailLogService detailLogService;
    private final StationService stationService;

    @PostMapping("/detailLog/getDetailLogList")
    public ResponseEntity<List<DetailLogListResponseDto>> getDetailLogList(@RequestBody LogIdRequestDto logIdRequestDto) {
        List<DetailLog> detailLogList = detailLogService.findDetailLogsByLogId(logIdRequestDto);
        List<DetailLogListResponseDto> detailLogListResponseDtoList = new ArrayList<>();

        for (DetailLog detailLog : detailLogList) {
            Long id = detailLog.getId();
            LocalDateTime fenceInTime = detailLog.getFenceInTime();
            LocalDateTime fenceOutTime = detailLog.getFenceOutTime();
            Long stationNumber = detailLog.getStationNumber();

            Station station = stationService.findByStationNumber(stationNumber);
            String name = station.getName();

            DetailLogListResponseDto responseDto = new DetailLogListResponseDto(id, fenceInTime, fenceOutTime, stationNumber, name);
            detailLogListResponseDtoList.add(responseDto);
        }

        return ResponseEntity.ok(detailLogListResponseDtoList);
    }

}
