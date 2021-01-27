package com.winterchen.service;

import com.winterchen.service.modules.admin.validator.ValidationResult;
import com.winterchen.service.modules.admin.validator.ValidatorImpl;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@SpringBootTest
class ServiceApplicationTests {
    @Data
    class TestValid {
        @NotBlank(message = "字符串不能为空!")
        private String str;
        @NotNull(message = "年龄不能不填写")
        @Min(value = 0,message = "最小0")
        @Max(value = 199, message = "最大199")
        private Integer age;
    }

    @Autowired
    private ValidatorImpl validator;

    @Test
    void contextLoads() {
        TestValid testValid = new TestValid();
        ValidationResult validationResult = validator.validate(testValid);
        System.out.println(validationResult.getHasError());

    }

}
