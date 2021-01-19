package com.winterchen.common.dto;

import com.winterchen.common.base.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:36 下午
 **/
@Data
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ApiModel("用户信息")
public class UserDTO extends BaseModel implements Serializable{

    private static final long serialVersionUID = -6184251877108652580L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("头像地址")
    private String avatarName;

    @ApiModelProperty("真实的头像地址")
    private String avatarPath;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("是否启用")
    private Boolean enabled;

    @ApiModelProperty("是否管理员")
    private Boolean isAdmin = false;

    @ApiModelProperty("密码重置时间")
    private Date pwdResetTime;

}
