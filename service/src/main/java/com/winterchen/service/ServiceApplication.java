package com.winterchen.service;

import com.winterchen.common.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.winterchen"})
@MapperScan({"com.winterchen.service.modules.admin.mapper",
        "com.winterchen.service.modules.security.mapper",
        "com.winterchen.service.modules.site.mapper"
})
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
