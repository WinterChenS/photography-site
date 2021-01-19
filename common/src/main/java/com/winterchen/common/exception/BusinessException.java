package com.winterchen.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:09 下午
 * @description 业务异常异常
 **/
public class BusinessException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
