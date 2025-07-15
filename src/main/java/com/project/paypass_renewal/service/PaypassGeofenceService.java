package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.PaypassGeofence;
import com.project.paypass_renewal.repository.PayPassGeofenceRepository;
import com.project.paypass_renewal.util.algorithm.PaypassAverageTimeAlgorithm;
import com.project.paypass_renewal.util.algorithm.PaypassDeleteDuplicateAlgorithm;
import com.project.paypass_renewal.util.algorithm.PaypassSequenceAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaypassGeofenceService {

    private final PayPassGeofenceRepository payPassGeofenceRepository;
    private final PaypassSequenceAlgorithm paypassSequenceAlgorithm;
    private final PaypassAverageTimeAlgorithm paypassAverageTimeAlgorithm;
    private final PaypassDeleteDuplicateAlgorithm paypassDeleteDuplicateAlgorithm;

    @Transactional
    private Map<List<PaypassGeofence>, List<String>> startPaypassAlgorithm(String number) {
        // number로 paypassGeofence 조회
        List<PaypassGeofence> geofenceList = payPassGeofenceRepository.findByNumber(number);

        Map<String, List<Long>> sequenceGeofenceMap = paypassSequenceAlgorithm.algorithmStart(geofenceList);
        Map<String, List<Long>> averageTimeGeofenceMap = paypassAverageTimeAlgorithm.algorithmStart(sequenceGeofenceMap, geofenceList);
        Map<List<PaypassGeofence>, List<String>> deleteDuplicateGeofenceMap = paypassDeleteDuplicateAlgorithm.algorithmStart(averageTimeGeofenceMap, geofenceList);

        return deleteDuplicateGeofenceMap;
    }

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
