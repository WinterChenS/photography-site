package com.winterchen.service.modules.admin.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author hongjun500
 * @date 2021/1/27 14:37
 * @tool ThinkPadX1隐士
 * Created with 2019.3.2.IntelliJ IDEA
 * Description: 数据校验返回结果
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    public <T> ValidationResult validate(T obj) {
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<T>> constraintViolationSet = validator.validate(obj);
        // 有错误
        if (constraintViolationSet.size() > 0) {
            validationResult.setHasError(true);
            constraintViolationSet.forEach(constraintViolation -> {
                // 错误信息
                String errMsg = constraintViolation.getMessage();
                // 错误字段
                String propertyName = constraintViolation.getPropertyPath().toString();
                validationResult.getErrorMsgMap().put(propertyName, errMsg);
            });
        }
        return validationResult;
    }

    /**
     * 当spring Bean初始化完成之后会回调这个ValidatorImpl
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 将hibernate的validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
