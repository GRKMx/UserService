package com.gorkemersizer.userservice.exception;

import com.gorkemersizer.userservice.enums.UserExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private UserExceptionEnum userExceptionEnum;
    public UserException(Class<?> className,UserExceptionEnum exceptionEnum){
        super(className.getName());
        setUserExceptionEnum(exceptionEnum);
    }
}
