package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.request.LinkRequestDto;
import com.project.paypass_renewal.domain.dto.request.SupporterNumberRequestDto;
import com.project.paypass_renewal.domain.dto.response.LinkListResponseDto;
import com.project.paypass_renewal.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<String> userNumbers = linkService.findUserNumbersBySupporterNumber(supporterNumberRequestDto);

        List<User> userList = linkService.findUserListByNumbers(userNumbers);

        List<LinkListResponseDto> linkListResponseDtoList = linkService.userToLinkResponseDto(userList);

        return ResponseEntity.ok(linkListResponseDtoList);
    }

}
