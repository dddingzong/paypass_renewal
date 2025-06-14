package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.request.LinkRequestDto;
import com.project.paypass_renewal.domain.dto.request.SupporterNumberRequestDto;
import com.project.paypass_renewal.domain.dto.response.LinkListResponseDto;
import com.project.paypass_renewal.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link/saveNewLink")
    public ResponseEntity<String> saveNewLink(@RequestBody LinkRequestDto linkRequestDto) {
        linkService.saveNewLink(linkRequestDto);
        return ResponseEntity.ok("saveSuccess");
    }

    @PostMapping("/link/getLinkList")
    public ResponseEntity<List<LinkListResponseDto>> getLinkList(@RequestBody SupporterNumberRequestDto supporterNumberRequestDto) {

        log.info("이용자 조회를 시작합니다.");

        List<String> userNumbers = linkService.findUserNumbersBySupporterNumber(supporterNumberRequestDto);

        log.info("이용자 번호 조회 완료: {}", userNumbers);

        List<User> userList = linkService.findUserListByNumbers(userNumbers);

        List<LinkListResponseDto> linkListResponseDtoList = linkService.userToLinkResponseDto(userList);

        return ResponseEntity.ok(linkListResponseDtoList);
    }

}
