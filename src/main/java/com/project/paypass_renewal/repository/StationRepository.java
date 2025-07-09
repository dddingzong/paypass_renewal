package com.project.paypass_renewal.repository;

import com.project.paypass_renewal.domain.data.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
