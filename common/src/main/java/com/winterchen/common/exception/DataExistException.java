package com.winterchen.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:03 下午
 * @description 数据重复异常
 **/
public class DataExistException extends RuntimeException{

    public DataExistException(Class clazz, String field, String val) {
        super(DataExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }

}
