package com.ourdus.protoourdus.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.ourdus.protoourdus.common.ApiResult.ERROR;

@ControllerAdvice
public class AllExceptionHandler {

    private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable.getMessage(), status), headers, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionAll(Exception e){
        return newResponse(e, HttpStatus.BAD_REQUEST);
    }
}
