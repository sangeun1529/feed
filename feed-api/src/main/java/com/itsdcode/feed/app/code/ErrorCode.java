package com.itsdcode.feed.app.code;

import com.itsdcode.feed.exception.PExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode implements PExceptionCode {

    FEED_BY_ID_NULL(1 , "feedId has no data");

    private int code;
    private String msg;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
