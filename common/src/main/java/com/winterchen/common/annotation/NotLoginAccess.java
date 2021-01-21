package com.winterchen.common.annotation;

import java.lang.annotation.*;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/20 9:50 上午
 * @description 未登录访问
 **/
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotLoginAccess {
}
