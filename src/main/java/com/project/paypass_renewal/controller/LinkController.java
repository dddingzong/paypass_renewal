package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.dto.LinkRequestDto;
import com.project.paypass_renewal.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/link/saveNewLink")
    public ResponseEntity<String> saveNewLink(@RequestBody LinkRequestDto linkRequestDto) {
        linkService.saveNewLink(linkRequestDto);
        return ResponseEntity.ok("saveSuccess");
    }

}
