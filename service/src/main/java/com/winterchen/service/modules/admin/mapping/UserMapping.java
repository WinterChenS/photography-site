package com.winterchen.service.modules.admin.mapping;

import com.winterchen.common.dto.UserDTO;
import com.winterchen.service.modules.admin.model.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:47 下午
 * @description TODO
 **/
@Mapper(componentModel = "spring")
public interface UserMapping {

    UserDTO toConvertToDto(User user);

    List<UserDTO> toConvertToDtos(List<User> users);

}
