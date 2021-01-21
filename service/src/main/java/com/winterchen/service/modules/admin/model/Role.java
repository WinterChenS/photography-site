package com.winterchen.service.modules.admin.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.winterchen.common.base.BaseModel;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * sys_role
 * @author 
 */
@Data
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
public class Role extends BaseModel {
    /**
     * ID
     */
    @TableId("role_id")
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色级别
     */
    private Integer level;

    /**
     * 描述
     */
    private String description;

    /**
     * 数据权限
     */
    private String dataScope;

    /**
     * 菜单列表 一对多
     */
    private Set<Menu> menus;


}