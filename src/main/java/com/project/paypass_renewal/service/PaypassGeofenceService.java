package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.PaypassGeofence;
import com.project.paypass_renewal.repository.PayPassGeofenceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaypassGeofenceService {

    private final PayPassGeofenceRepository payPassGeofenceRepository;

    @Transactional
    public PaypassGeofence createGeofenceLocation(String number, Long stationNumber, String busInfo) {
        PaypassGeofence paypassGeofence = new PaypassGeofence(number, stationNumber, busInfo);
        payPassGeofenceRepository.save(paypassGeofence);

        return paypassGeofence;
    }

    public List<PaypassGeofence> findByNumberAndStationNumber(String number, Long stationNumber) {
        return payPassGeofenceRepository.findByNumberAndStationNumber(number, stationNumber);

    }

    public PaypassGeofence userFenceOutWithoutEntity(String number, Long stationNumber, String busInfo, LocalDateTime userFenceOut) {
        return new PaypassGeofence(number, stationNumber, busInfo, userFenceOut);
    }

    @Transactional
    public PaypassGeofence save(PaypassGeofence paypassGeofence) {
        return payPassGeofenceRepository.save(paypassGeofence);
    }

    @Transactional
    public void userFenceOut(PaypassGeofence paypassGeofence) {
        paypassGeofence.userFenceOut();
    }

    @Transactional
    public boolean fenceOutTimeIsNull(PaypassGeofence paypassGeofence){
        return paypassGeofence.fenceOutTimeIsNull();
    }



}
