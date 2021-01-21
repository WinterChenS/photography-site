package com.winterchen.service.modules.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 3:22 下午
 * @description 在线用户
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserDto {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;


    /**
     * 浏览器
     */
    private String browser;

    /**
     * IP
     */
    private String ip;

    /**
     * 地址
     */
    private String address;

    /**
     * token
     */
    private String key;

    /**
     * 登录时间
     */
    private Date loginTime;
}
