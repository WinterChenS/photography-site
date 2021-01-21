package com.winterchen.common.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.winterchen.common.base.BusinessAssert;
import com.winterchen.common.enums.DataScopeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 3:43 下午
 **/
@Slf4j
public class SecurityUtils {
    /**
     * 获取当前登录的用户
     * @return UserDetails
     */
    public static UserDetails getCurrentUser() {
        UserDetailsService userDetailsService = SpringContextHolder.getBean(UserDetailsService.class);
        return userDetailsService.loadUserByUsername(getCurrentUsername());
    }

    /**
     * 获取系统用户名称
     *
     * @return 系统用户名称
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        BusinessAssert.meetDefaultCond(authentication == null, "当前登录状态过期");
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        BusinessAssert.meetDefaultCond(true, "找不到当前登录的信息");
        return null;
    }

    /**
     * 获取系统用户ID
     * @return 系统用户ID
     */
    public static Long getCurrentUserId() {
        UserDetails userDetails = getCurrentUser();
        return new JSONObject(new JSONObject(userDetails).get("user")).get("id", Long.class);
    }

    /**
     * 获取当前用户的数据权限
     * @return /
     */
    public static List<Long> getCurrentUserDataScope(){
        UserDetails userDetails = getCurrentUser();
        JSONArray array = JSONUtil.parseArray(new JSONObject(userDetails).get("dataScopes"));
        return JSONUtil.toList(array,Long.class);
    }

    /**
     * 获取数据权限级别
     * @return 级别
     */
    public static String getDataScopeType() {
        List<Long> dataScopes = getCurrentUserDataScope();
        if(dataScopes.size() != 0){
            return "";
        }
        return DataScopeEnum.ALL.getValue();
    }
}
