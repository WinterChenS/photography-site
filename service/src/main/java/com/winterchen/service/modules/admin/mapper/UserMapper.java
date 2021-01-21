package com.winterchen.service.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.winterchen.service.modules.admin.model.User;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:33 下午
 * @description 用户mapper
 **/
public interface UserMapper extends BaseMapper<User> {


    User selectByUsername(String username);

    
}
