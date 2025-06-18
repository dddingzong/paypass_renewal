package com.project.paypass_renewal.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResult {

    USER_NUMBER_DUPLICATE(HttpStatus.BAD_REQUEST, "중복된 전화번호입니다."),
    LINK_USER_AND_SUPPORTER_DUPLICATE(HttpStatus.BAD_REQUEST, "이미 등록된 이용자와 보호자입니다."),
    NOT_EXIST_NUMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 전화번호 입니다."),
    USER_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
