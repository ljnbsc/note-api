package com.shisan.note.controller.auth;

import cn.shisan.common.domain.common.JResult;
import com.shisan.note.controller.BaseController;
import com.shisan.note.dto.admin.PermissionTree;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.entity.Permission;
import com.shisan.note.entity.Role;
import com.shisan.note.service.PermissionService;
import com.shisan.note.service.RoleService;
import com.shisan.note.service.UserService;
import com.shisan.note.utils.JwtUtil;
import com.shisan.note.dto.auth.LoginDto;
import com.shisan.note.entity.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "授权认证API")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final PermissionService permissionService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public JResult<String> authenticate(@RequestBody LoginDto loginDto) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String jwtToken = jwtUtil.generateToken(loginUser);
        return success(jwtToken);
    }

    @ApiOperation("注册用户")
    @PostMapping("/register")
    public JResult<String> authenticate(@RequestBody UserRegister register) {
        register.setPassword(passwordEncoder.encode(register.getPassword()));
        userService.register(register);
        return success();
    }

    @ApiOperation("用户菜单")
    @GetMapping("/userMenu")
    public JResult<List<PermissionTree>> userMenu() {
        List<PermissionTree> permission = permissionService.userMenu(getUserId());
        return success(permission);
    }

    @ApiOperation("用户角色")
    @GetMapping("/userRole")
    public JResult<List<Role>> userRole() {
        List<Role> role = roleService.findByUserId(getUserId());
        return success(role);
    }

    @ApiOperation("用户资源权限（启用有效的） ")
    @GetMapping("/permissionList")
    public JResult<List<Permission>> permissionList(@RequestParam(required = false) @ApiParam(name = "类型 1菜单 2api") Integer type) {
        List<Permission> permission = permissionService.findByUserId(getUserId(), type);
        return success(permission);
    }
}
