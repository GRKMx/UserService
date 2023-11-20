package com.gorkemersizer.userservice.base;

import com.gorkemersizer.userservice.enums.UserExceptionEnum;
import com.gorkemersizer.userservice.exception.UserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
@ControllerAdvice
public class ResponseHandler {

    public static <T>BaseResponse<T> responseEntityHandler (HttpStatus responseCode, T responseObject, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setMessage(message);
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }

    public static <T>BaseResponse<T> responseEntityHandler (HttpStatus responseCode, T responseObject, UserExceptionEnum exception) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setErrorCode(exception.getCode());
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }

    public static <T>BaseResponse<T> responseEntityHandler (HttpStatus responseCode, T responseObject) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setStatus(responseCode.value());
        response.setData(responseObject);
        return response;
    }

    public static ResponseEntity<Object> responseEntityHandler (HttpStatus responseCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("status",responseCode.value());
        return new ResponseEntity<>(map,responseCode);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String,Object> body = new HashMap<>();
        body.put("message", Objects.requireNonNull(exception.getDetailMessageArguments())[1]);
        body.put("status",HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleUserException(UserException exception) {
        Map<String,Object> body = new HashMap<>();
        body.put("message",exception.getUserExceptionEnum().getMessage());
        body.put("errorCode",exception.getUserExceptionEnum().getCode());
        body.put("status",exception.getUserExceptionEnum().getHttpStatus().value());
        return new ResponseEntity<>(body,exception.getUserExceptionEnum().getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return handleUserException(new UserException(ResponseHandler.class,UserExceptionEnum.VALIDATION_ERROR));
    }
}
