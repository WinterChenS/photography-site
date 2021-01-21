package com.winterchen.service.modules.admin.service;

import com.winterchen.service.modules.admin.dto.UserDTO;
import com.winterchen.service.modules.admin.mapper.RoleMapper;
import com.winterchen.service.modules.admin.model.Menu;
import com.winterchen.service.modules.admin.model.Role;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 9:49 上午
 * @description TODO
 **/
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleMapper roleMapper;


    public List<GrantedAuthority> getGrantedAuthorities(UserDTO userDTO) {
        Set<String> permissions = new HashSet<>();
        if (userDTO.getIsAdmin()) {
            permissions.add("admin");
            return permissions.stream().map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }

        List<Role> roles = roleMapper.selectByUserId(userDTO.getUserId());
        permissions = roles.stream().flatMap(role -> role.getMenus().stream())
                .filter(menu -> StringUtils.isNoneBlank(menu.getPermission()))
                .map(Menu::getPermission)
                .collect(Collectors.toSet());
        return permissions.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
