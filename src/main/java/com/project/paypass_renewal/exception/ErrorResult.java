package com.project.paypass_renewal.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorResult {

    USER_EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST, "중복된 이메일입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
