package com.winterchen.common.exception.handler;

import com.winterchen.common.base.APIResponse;
import com.winterchen.common.exception.BusinessException;
import com.winterchen.common.exception.DataExistException;
import com.winterchen.common.exception.NotFundException;
import com.winterchen.common.utils.ThrowableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:13 下午
 * @description 全局异常处理
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     */
    @ExceptionHandler(Throwable.class)
    public APIResponse<?> handleException(Throwable e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return APIResponse.fail(BAD_REQUEST, e.getMessage());
    }

    /**
     * BadCredentialsException
     */
    @ExceptionHandler(BadCredentialsException.class)
    public APIResponse<?> badCredentialsException(BadCredentialsException e){
        // 打印堆栈信息
        String message = "坏的凭证".equals(e.getMessage()) ? "用户名或密码不正确" : e.getMessage();
        log.error(message);
        return APIResponse.fail(UNAUTHORIZED, message);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public APIResponse<?> badRequestException(BusinessException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return APIResponse.fail(BAD_REQUEST, e.getMessage());
    }

    /**
     * 处理 Exist
     */
    @ExceptionHandler(value = DataExistException.class)
    public APIResponse<?> entityExistException(DataExistException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return APIResponse.fail(BAD_REQUEST, e.getMessage());
    }

    /**
     * 处理 NotFound
     */
    @ExceptionHandler(value = NotFundException.class)
    public APIResponse<?> entityNotFoundException(NotFundException e) {
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        return APIResponse.fail(NOT_FOUND, e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public APIResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        // 打印堆栈信息
        log.error(ThrowableUtil.getStackTrace(e));
        String[] str = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getCodes())[1].split("\\.");
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String msg = "不能为空";
        if(msg.equals(message)){
            message = str[1] + ":" + message;
        }
        return APIResponse.fail(BAD_REQUEST, message);
    }


}
