package com.winterchen.service.modules.security.service;

import com.winterchen.common.base.BusinessAssert;
import com.winterchen.common.exception.NotFundException;
import com.winterchen.service.modules.admin.dto.UserDTO;
import com.winterchen.service.modules.admin.service.RoleService;
import com.winterchen.service.modules.admin.service.UserService;
import com.winterchen.service.modules.security.config.bean.LoginProperties;
import com.winterchen.service.modules.security.dto.JwtUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/20 4:46 下午
 * @description 实现security的服务，定义用户名信息查询
 **/
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;
    private final LoginProperties loginProperties;
    public void setEnableCache(boolean enableCache) {
        this.loginProperties.setCacheEnable(enableCache);
    }

    /**
     * 用户信息缓存
     *
     * @see {@link UserCacheCleanService}
     */
    static Map<String, JwtUserDto> userDtoCache = new ConcurrentHashMap<>();

    @Override
    public JwtUserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean hasCache = false;
        JwtUserDto jwtUserDto = null;
        if (loginProperties.isCacheEnable() && userDtoCache.containsKey(username)) {
            jwtUserDto = userDtoCache.get(username);
            hasCache = true;
        }
        if (!hasCache) {
            UserDTO user;
            try {
                user = userService.findByName(username);
            } catch (NotFundException e) {
                throw new UsernameNotFoundException("", e);
            }
            if (null == user) {
                throw new UsernameNotFoundException("");
            } else {
                BusinessAssert.meetDefaultCond(!user.getEnabled(), "账号未激活!");
            }
            jwtUserDto = new JwtUserDto(user, roleService.getGrantedAuthorities(user));
        }
        return jwtUserDto;
    }
}
