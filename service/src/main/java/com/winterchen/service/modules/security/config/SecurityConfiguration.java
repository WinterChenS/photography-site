package com.winterchen.service.modules.security.config;

import com.winterchen.service.modules.security.config.bean.LoginProperties;
import com.winterchen.service.modules.security.config.bean.SecurityProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 2:37 下午
 * @description TODO
 **/
@Configuration
public class SecurityConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "login", ignoreUnknownFields = true)
    public LoginProperties loginProperties() {
        return new LoginProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = true)
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }
}
