package com.winterchen.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 11:29 上午
 * @description 无找到异常
 **/
@Getter
public class NotFundException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public NotFundException() {
        super(BAD_REQUEST.getReasonPhrase());
    }

    public NotFundException(String msg){
        super(msg);
    }

    public NotFundException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
