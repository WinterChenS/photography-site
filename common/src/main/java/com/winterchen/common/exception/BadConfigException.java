package com.winterchen.common.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:10 下午
 * @description 配置错误异常
 **/
public class BadConfigException extends RuntimeException {
    private Integer status = INTERNAL_SERVER_ERROR.value();

    public BadConfigException(String msg){
        super(msg);
    }

    public BadConfigException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
