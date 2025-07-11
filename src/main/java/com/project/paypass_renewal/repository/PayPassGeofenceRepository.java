package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.PaypassGeofence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayPassGeofenceRepository extends JpaRepository<PaypassGeofence, Long> {

}
