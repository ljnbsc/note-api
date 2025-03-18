package com.shisan.note.controller.admin;

import cn.shisan.common.domain.common.JResult;
import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.domain.common.PageResult;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.admin.UserDto;
import com.shisan.note.dto.admin.UserRoleDto;
import com.shisan.note.dto.admin.UserStatusDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Api(tags = "用户管理API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    @ApiOperation("设置用户角色")
    @PostMapping("/setRole")
    public JResult<String> setRole(@RequestBody UserRoleDto userRoleDto) {
        userService.setRole(userRoleDto);
        return success();
    }

    @ApiOperation("用户分页")
    @PostMapping("/page")
    public JResult<PageResult<UserDto>> page(@RequestBody PageQuery<UserQueryDto> query) {
        PageResult<UserDto> page = userService.pageList(query);
        return success(page);
    }

    @ApiOperation("修改用户状态")
    @PostMapping("/updateStatus")
    public JResult<String> updateStatus(@RequestBody UserStatusDto statusDto) {
        userService.updateStatus(statusDto);
        return success();
    }


}