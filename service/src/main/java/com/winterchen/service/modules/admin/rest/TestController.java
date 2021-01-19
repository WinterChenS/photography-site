package com.winterchen.service.modules.admin.rest;

import com.winterchen.common.base.APIResponse;
import com.winterchen.common.dto.UserDTO;
import com.winterchen.service.modules.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 1:37 下午
 * @description TODO
 **/
@Api(tags = "test")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @ApiOperation("test")
    @GetMapping("")
    public APIResponse<?> test() {
        return APIResponse.success();
    }

    @ApiOperation("查找用户信息列表")
    @GetMapping("/users")
    public APIResponse<List<UserDTO>> listUser() {
        return APIResponse.success(userService.listUser());
    }

    @ApiOperation("根据编号查找用户信息")
    @GetMapping("/user")
    public APIResponse<UserDTO> findUserById(
            @ApiParam(name = "id", value = "编号", required = false)
            @RequestParam(name = "id", required = false)
            Long id
    ) {
        return APIResponse.success(userService.findUserById(id));
    }
}
