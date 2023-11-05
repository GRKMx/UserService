package com.gorkemersizer.userservice.exception;

import com.gorkemersizer.userservice.enums.UserExceptionEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final UserExceptionEnum userExceptionEnum;
}
