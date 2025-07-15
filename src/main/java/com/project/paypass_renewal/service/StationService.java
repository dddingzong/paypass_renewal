package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.data.Station;
import com.project.paypass_renewal.domain.dto.response.StationListResponseDto;
import com.project.paypass_renewal.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    private final StationRepository stationRepository;

    public List<StationListResponseDto> getStationList() {
        List<Station> stationList = stationRepository.findAll();

        return stationList.stream().map(
                station -> new StationListResponseDto(
                        station.getName(),
                        station.getStationNumber(),
                        station.getLongitude(),
                        station.getLatitude()
                )
        ).toList();
    }

    public String findBusInfoByStationNumber(Long stationNumber) {
        return stationRepository.findBusInfoByStationNumber(stationNumber);
    }

}
