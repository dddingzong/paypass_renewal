package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.Log;
import com.project.paypass_renewal.domain.dto.request.NumberRequestDto;
import com.project.paypass_renewal.domain.dto.response.LogListResponseDto;
import com.project.paypass_renewal.repository.LogRepository;
import com.project.paypass_renewal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final UserRepository userRepository;

    public List<LogListResponseDto> getLogListByNumber(NumberRequestDto numberRequestDto) {
        List<LogListResponseDto> logListResponseDtoList = new ArrayList<>();

        String number = numberRequestDto.getNumber();

        List<Log> logList = logRepository.findByNumberOrderByDepartureTimeDesc(number);

        for (Log log : logList) {
            LogListResponseDto logListResponseDto = logToLogListResponseDto(log);
            logListResponseDtoList.add(logListResponseDto);
        }
        
        return logListResponseDtoList;
    }

    private LogListResponseDto logToLogListResponseDto(Log log) {
        String number = log.getNumber();
        LocalDateTime departureTime = log.getDepartureTime();
        LocalDateTime arrivalTime = log.getArrivalTime();
        String departureLocation = log.getDepartureLocation();
        String arrivalLocation = log.getArrivalLocation();

        String name = userRepository.findByNumber(number).getName();

        return new LogListResponseDto(number, name, departureTime, arrivalTime, departureLocation, arrivalLocation);

    }

    public void saveLog(Log log) {
        logRepository.save(log);
    }

}
