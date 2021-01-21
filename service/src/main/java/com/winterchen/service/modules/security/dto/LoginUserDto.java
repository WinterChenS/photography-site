package com.winterchen.service.modules.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 3:35 下午
 * @description 登录用户信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String code;

    private String uuid = "";
}
