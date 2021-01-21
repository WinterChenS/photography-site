package com.winterchen.service.modules.admin.mapping;

import com.winterchen.service.modules.admin.dto.UserDTO;
import com.winterchen.service.modules.admin.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:47 下午
 * @description TODO
 **/
@Mapper(componentModel = "spring")
public abstract class UserMapping {

    public abstract UserDTO toConvertToDto(User user);

    public abstract List<UserDTO> toConvertToDtos(List<User> users);

    @AfterMapping
    public UserDTO afterMapping(User user, @MappingTarget UserDTO.UserDTOBuilder target) {
        return target.build();
    }

}
