package com.project.paypass_renewal.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResult {

    USER_NUMBER_DUPLICATE(HttpStatus.BAD_REQUEST, "중복된 전화번호입니다."),
    LINK_USER_AND_SUPPORTER_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 등록된 이용자와 보호자입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
