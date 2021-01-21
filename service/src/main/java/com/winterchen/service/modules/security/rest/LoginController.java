package com.winterchen.service.modules.security.rest;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.base.Captcha;
import com.winterchen.common.annotation.rest.AnonymousDeleteMapping;
import com.winterchen.common.annotation.rest.AnonymousGetMapping;
import com.winterchen.common.annotation.rest.AnonymousPostMapping;
import com.winterchen.common.base.APIResponse;
import com.winterchen.common.base.BusinessAssert;
import com.winterchen.common.configuration.RsaProperties;
import com.winterchen.common.utils.RedisUtils;
import com.winterchen.common.utils.RsaUtils;
import com.winterchen.common.utils.SecurityUtils;
import com.winterchen.common.utils.StringUtils;
import com.winterchen.service.modules.security.config.bean.LoginProperties;
import com.winterchen.service.modules.security.config.bean.SecurityProperties;
import com.winterchen.service.modules.security.dto.JwtUserDto;
import com.winterchen.service.modules.security.dto.LoginUserDto;
import com.winterchen.service.modules.security.enums.LoginCodeEnum;
import com.winterchen.service.modules.security.security.TokenProvider;
import com.winterchen.service.modules.security.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/21 3:34 下午
 * @description 登录控制器
 **/
@Api(tags = "登录API")
@RequestMapping("/login")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public APIResponse<Object> login(@Validated @RequestBody LoginUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        BusinessAssert.meetDefaultCond(StringUtils.isBlank(code), "验证码不存在或已过期");
        BusinessAssert.meetDefaultCond(StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code),
                "验证码错误");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = tokenProvider.createToken(authentication);
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 保存在线信息
        onlineUserService.save(jwtUserDto, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return APIResponse.success(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public APIResponse<Object> getUserInfo() {
        return APIResponse.success(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public APIResponse<Object> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return APIResponse.success(imgResult);
    }

    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public APIResponse<Object> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return APIResponse.success();
    }

}
