package com.winterchen.service.modules.security.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.winterchen.service.modules.admin.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/20 5:13 下午
 * @description TODO
 **/
@Getter
@AllArgsConstructor
public class JwtUserDto implements UserDetails {

    private final UserDTO user;

    @JSONField(serialize = false)
    private final List<GrantedAuthority> authorities;

    @Override
    @JSONField(serialize = false)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return user.getEnabled();
    }


}
