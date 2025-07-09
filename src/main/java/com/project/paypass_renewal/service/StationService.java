package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.data.Station;
import com.project.paypass_renewal.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public List<Station> findAll() {
       return stationRepository.findAll();
    }

}
