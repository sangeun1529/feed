package com.itsdcode.feed.app.controller.advice;

import com.itsdcode.feed.exception.PException;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class FeedAdvice {

    @ExceptionHandler(PException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String exceptionController(PException e){
        log.error(String.format("error code : %d , error messge : %s" , e.getErrorNumber() , e.getErrorMsg()));
        return e.getMessage(); //response JSON 포멧 지정시 변경
    }
}
