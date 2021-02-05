package ourdus.ourdusspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ourdus.ourdusspring.common.ApiResult;

import java.util.NoSuchElementException;

import static ourdus.ourdusspring.common.ApiResult.ERROR;

@RestControllerAdvice
@Slf4j
public class GlobalRestExceptionHandler {

    private ResponseEntity<ApiResult<?>> newResponse(Throwable throwable, HttpStatus status){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ERROR(throwable.getMessage(), status), headers, status);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> internalServerError(Exception e){
//        Map<String,Object> resultMap = new HashMap<>();
//        resultMap.put("msg",e.getMessage());
        return newResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalStateException.class, NoSuchElementException.class})
    public ResponseEntity<?> IllegalStateExceptionError(Exception e){
        return newResponse(e, HttpStatus.NOT_FOUND);
    }





}
