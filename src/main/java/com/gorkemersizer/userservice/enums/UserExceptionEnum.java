package com.gorkemersizer.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum UserExceptionEnum {

    VALIDATION_ERROR("0001", "Validation error", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("1001", "User not found", HttpStatus.NOT_FOUND),
    RESOURCE_NOT_FOUND("1002", "Resource not found", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
