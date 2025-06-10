package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLocationRepository extends JpaRepository<UserLocation, Long> {
}
