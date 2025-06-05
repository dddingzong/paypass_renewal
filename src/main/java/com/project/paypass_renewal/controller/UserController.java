package com.project.paypass_renewal.controller;

import com.project.paypass_renewal.domain.User;
import com.project.paypass_renewal.domain.dto.UserDto;
import com.project.paypass_renewal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login/newUser")
    public ResponseEntity<Map<String, Object>> createNewUser(@RequestBody UserDto userDto){

        log.info("사용자 신규 회원가입 요청");

        User user = userService.saveNewUser(userDto);

        Map<String, Object> response = new HashMap<>();
        response.put("mainId", user.getMainId());

        log.info("사용자 이름: " + user.getName() + ", 성공적으로 회원가입 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

}
