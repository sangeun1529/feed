package com.itsdcode.feed.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class PException extends RuntimeException{

    private String errorMsg;
    private int errorNumber;

    public PException(PExceptionCode pExceptionCode){
        super(pExceptionCode.getMsg());
        errorNumber = pExceptionCode.getCode();
        errorMsg = pExceptionCode.getMsg();

    }
}
