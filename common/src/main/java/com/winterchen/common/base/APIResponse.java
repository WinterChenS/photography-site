package com.winterchen.common.base;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 11:05 上午
 * @description 统一返回类
 **/
@Data
@Builder
public class APIResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;


    public static APIResponse success(){
        return APIResponse.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    public static APIResponse success(Object data) {
        return APIResponse.builder()
                .code(HttpStatus.OK.value())
                .data(data)
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    public static APIResponse fail(HttpStatus status){
        return APIResponse.builder()
                .code(status.value())
                .message(status.getReasonPhrase())
                .build();
    }

    public static APIResponse fail(HttpStatus status, String message){
        return APIResponse.builder()
                .code(status.value())
                .message(message)
                .build();
    }

}
