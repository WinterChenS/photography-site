package com.winterchen.service.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winterchen.service.modules.admin.model.Menu;
import com.winterchen.service.modules.admin.model.Role;

import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 09:46 上午
 * @description 权限mapper
 **/
public interface RoleMapper extends BaseMapper<Role> {


    List<Role> selectByUserId(Long userId);

    List<Menu> findMenusByRoleId(String roleId);
}