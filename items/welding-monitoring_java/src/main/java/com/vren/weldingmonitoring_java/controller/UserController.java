package com.vren.weldingmonitoring_java.controller;

import com.vren.weldingmonitoring_java.common.domain.PageResult;
import com.vren.weldingmonitoring_java.common.domain.ResponseResult;
import com.vren.weldingmonitoring_java.domain.dto.UserDTO;
import com.vren.weldingmonitoring_java.domain.dto.UserLoginDTO;
import com.vren.weldingmonitoring_java.domain.dto.UserQueryDTO;
import com.vren.weldingmonitoring_java.domain.dto.UserSingleDTO;
import com.vren.weldingmonitoring_java.domain.vo.UserVO;
import com.vren.weldingmonitoring_java.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
    @ApiOperation("新增或编辑用户信息")
    public ResponseResult<Boolean> register(@RequestBody @Valid UserDTO dto) {
        userService.addOrUpdateUser(dto);
        return ResponseResult.success("操作成功");
    }

    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    @ApiOperation("返回用户列表")
    public ResponseResult<PageResult<UserVO>> selectList(@RequestBody UserQueryDTO dto) {
        return ResponseResult.success("操作成功", userService.queryList(dto));
    }

    @RequestMapping(value = "/selectUserVO", method = RequestMethod.POST)
    @ApiOperation("返回单个用户")
    public ResponseResult<UserVO> selectUserVO(@RequestBody UserSingleDTO dto) {
        return ResponseResult.success("操作成功", userService.selectUserVO(dto));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("登录")
    public ResponseResult<UserVO> login(@RequestBody UserLoginDTO dto) {
        UserVO userVO = userService.login(dto);
        return ResponseResult.success("操作成功", userVO);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ApiOperation("删除单个用户")
    public ResponseResult<Boolean> deleteUser(@RequestBody UserSingleDTO dto) {
        userService.deleteUser(dto);
        return ResponseResult.success("操作成功");
    }
}
