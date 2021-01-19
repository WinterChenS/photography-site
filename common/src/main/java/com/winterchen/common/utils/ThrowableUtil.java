package com.winterchen.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:15 下午
 * @description 异常工具类
 **/
public class ThrowableUtil {
    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}
