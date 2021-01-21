package com.winterchen.service.modules.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.winterchen.common.base.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:26 下午
 * @description 用户
 **/
@Data
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class User extends BaseModel {

    @TableId("user_id")
    private Long userId;

    private String username;

    private String nickName;

    private String email;

    private String phone;

    private String gender;

    private String avatarName;

    private String avatarPath;

    private String password;

    private Boolean enabled;

    private Boolean isAdmin = false;

    private Date pwdResetTime;

}
