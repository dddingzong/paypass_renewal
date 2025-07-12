package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.PaypassGeofence;
import com.project.paypass_renewal.repository.PayPassGeofenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaypassGeofenceService {

    private final PayPassGeofenceRepository payPassGeofenceRepository;


    public PaypassGeofence createGeofenceLocation(String number, Long stationNumber, String busInfo) {
        PaypassGeofence paypassGeofence = new PaypassGeofence(number, stationNumber, busInfo);
        payPassGeofenceRepository.save(paypassGeofence);

        return paypassGeofence;

    }

}
