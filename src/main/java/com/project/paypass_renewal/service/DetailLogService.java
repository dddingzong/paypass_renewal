package com.project.paypass_renewal.service;

import com.project.paypass_renewal.domain.DetailLog;
import com.project.paypass_renewal.domain.dto.request.LogIdRequestDto;
import com.project.paypass_renewal.repository.DetailLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailLogService {

    DetailLogRepository detailLogRepository;

    public List<DetailLog> findDetailLogsByLogId(LogIdRequestDto logIdRequestDto) {
        Long logId = logIdRequestDto.getLogId();
        return detailLogRepository.findByLogId(logId);
    }


}
