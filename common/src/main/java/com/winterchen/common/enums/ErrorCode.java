package com.winterchen.common.enums;

import com.winterchen.common.base.ResponseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 3:53 下午
 * @description 业务异常代码
 **/
@Getter
@AllArgsConstructor
public enum ErrorCode implements ResponseCodeInterface {

    VALIDATE_FAILED(400, "参数检验失败"),
    ;

    private int code;

    private String message;

}
