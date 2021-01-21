package com.winterchen.common.base;

import com.winterchen.common.exception.BadConfigException;
import com.winterchen.common.exception.BusinessException;
import com.winterchen.common.exception.DataExistException;
import com.winterchen.common.exception.NotFundException;
import org.springframework.util.ObjectUtils;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 3:44 下午
 * @description 业务断言
 **/
public class BusinessAssert {


    public static void meetDefaultCond(boolean condition, String message) {
        if (condition) {
            throw new BusinessException(message);
        }
    }

    public static void meetDefaultCond(boolean condition, ResponseCodeInterface responseCodeInterface, String message) {
        if (condition) {
            throw new BusinessException(message);
        }
    }

    public static void meetNotFundCond(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new NotFundException();
        }
    }

    public static void meetNotFundCond(Object obj, String message) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new NotFundException(message);
        }
    }

    public static void meetNotFundCond(boolean condition, String message) {
        if (condition) {
            throw new NotFundException(message);
        }
    }

    public static void assertBadConfig() {
        throw new BadConfigException();
    }

    public static void assertBadConfig(String message) {
        throw new BadConfigException(message);
    }

    public static void meetDataExistCond(Object obj) {
        if (!ObjectUtils.isEmpty(obj)) {
            throw new DataExistException(obj.getClass());
        }
    }


    public static void meetDataExistCond(Object obj, String field, String value) {
        if (!ObjectUtils.isEmpty(obj)) {
            throw new DataExistException(obj.getClass(), field, value);
        }
    }
}
