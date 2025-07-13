package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.PaypassGeofence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayPassGeofenceRepository extends JpaRepository<PaypassGeofence, Long> {
    List<PaypassGeofence> findByNumberAndStationNumber(String number, Long stationNumber);
}
