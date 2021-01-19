package com.winterchen.common.configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:25 下午
 * @description TODO
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4jConfiguration {

    /**
     * 是否允许swagger
     */
    @Value("${swagger.enable:true}")
    private Boolean enableSwagger;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Contact contact = new Contact("winter chen","https://blog.winterchen.com/","i@winterchen.com");
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //.title("swagger-bootstrap-ui-demo RESTful APIs")
                        .description("Photography site Api")
                        .termsOfServiceUrl("https://blog.winterchen.com/")
                        .contact(contact)
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("v1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.winterchen.service.modules"))
                .paths(PathSelectors.any())
                .build().enable(enableSwagger);
        return docket;
    }
    
}
