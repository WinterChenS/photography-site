package com.winterchen.service.modules.admin.service;

import com.winterchen.common.dto.UserDTO;
import com.winterchen.service.modules.admin.mapper.UserMapper;
import com.winterchen.service.modules.admin.mapping.UserMapping;
import com.winterchen.service.modules.admin.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:34 下午
 * @description 用户服务
 **/
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapping userMapping;


    public List<UserDTO> findUserList(){
        List<User> users = userMapper.selectList(null);
        return userMapping.toConvertToDtos(users);
    }

}
