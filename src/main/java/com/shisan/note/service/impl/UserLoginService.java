package com.shisan.note.service.impl;

import com.shisan.note.common.enums.UserEnums;
import com.shisan.note.entity.LoginUser;
import com.shisan.note.entity.Permission;
import com.shisan.note.entity.User;
import com.shisan.note.service.PermissionService;
import com.shisan.note.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {

    private final UserService userService;
    private final PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //如果User为null，就会抛出异常信息：UsernameNotFoundException
        LoginUser loginUser = null;
        User user = userService.findByUserName(username);
        if (null != user) {
            loginUser = new LoginUser();
            loginUser.setUserId(user.getId());
            loginUser.setUserName(user.getUserName());
            loginUser.setPassword(user.getPassword());
            ArrayList<String> roles = new ArrayList<>();
            boolean admin = permissionService.isAdmin(user.getId());
            if (!admin) {
                // TODO 之后考虑用redis
                List<Permission> permissions = permissionService.findByUserId(user.getId(), UserEnums.PermissionType.API.getCode());
                for (Permission permission : permissions) {
                    roles.add(permission.getUrl());
                }
            }
            loginUser.setAdmin(admin);
            loginUser.setPermissions(roles);
            loginUser.setEnabled(user.getStatus() != 2);
        }
        if (loginUser == null) {
            throw new UsernameNotFoundException("用户不存在!");
        } else if (!loginUser.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!loginUser.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!loginUser.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return loginUser;
    }
}

