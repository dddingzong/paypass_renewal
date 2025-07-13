package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.dto.request.UserPaypassGeofenceRequestDto;
import com.project.paypass_renewal.service.PaypassGeofenceService;
import com.project.paypass_renewal.service.StationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class PayPassGeofenceController {

    private final StationService stationService;
    private final PaypassGeofenceService paypassGeofenceService;

    @PostMapping("/geofence/userFenceIn")
    public ResponseEntity<String> userFenceIn(@RequestBody UserPaypassGeofenceRequestDto userPaypassGeofenceRequestDto) {
        log.info("****************************************************************");
        log.info("사용자가 geofence에 접근했기 때문에 userGeofenceIn method를 실행합니다.");
        log.info("****************************************************************");

        String number = userPaypassGeofenceRequestDto.getNumber();
        Long stationNumber = userPaypassGeofenceRequestDto.getStationNumber();

        // statationNumber로 busInfo 가져오기
        String busInfo = stationService.findBusInfoByStationNumber(stationNumber);

        // geofenceLocation entity 생성
        paypassGeofenceService.createGeofenceLocation(number, stationNumber, busInfo);

        return ResponseEntity.ok("success save geofence data");
    }

//    @PostMapping("/geofence/userFenceOut")
//    public ResponseEntity<PaypassGeofence> userFenceOut(@RequestBody UserPaypassGeofenceRequestDto userPaypassGeofenceRequestDto) {
//        log.info("****************************************************************");
//        log.info("사용자가 geofence에서 이탈했기 때문에 userGeofenceOut method를 실행합니다.");
//        log.info("****************************************************************");
//
//        String number = userPaypassGeofenceRequestDto.getNumber();
//        Long stationNumber = userPaypassGeofenceRequestDto.getStationNumber();
//
//        // mainId와 stationNumber를 활용해서 해당 entity 가져오기
//        List<PaypassGeofence> paypassGeofences = paypassGeofenceService.findByMainIdAndStationNumber(mainId, stationNumber);
//
//
//        return null;
//    }


}
