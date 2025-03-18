package com.shisan.note.service.impl;

import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.domain.common.PageResult;
import cn.shisan.common.domain.enums.IEnum;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shisan.note.common.enums.StatusEnums;
import com.shisan.note.dto.admin.UserDto;
import com.shisan.note.dto.auth.UserRegister;
import com.shisan.note.dto.admin.UserRoleDto;
import com.shisan.note.dto.admin.UserStatusDto;
import com.shisan.note.dto.query.UserQueryDto;
import com.shisan.note.entity.User;
import com.shisan.note.entity.UserRole;
import com.shisan.note.mapper.admin.UserMapper;
import com.shisan.note.service.UserRoleService;
import com.shisan.note.service.UserService;
import com.shisan.note.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserRoleService userRoleService;


    @Override
    public void register(UserRegister register) {
        AssertUtils.isBlank(register.getUserName(), "用户名不能为空");
        AssertUtils.isBlank(register.getPassword(), "密码不能为空");

        User name = findByUserName(register.getUserName());
        AssertUtils.notNull(name, "用户名已经存在");

        User user = new User();
        BeanUtils.copyProperties(register, user);
        user.setCreated(LocalDateTime.now());
        user.setStatus(StatusEnums.ENABLE.getCode());
        userMapper.insert(user);
    }

    @Override
    public void setRole(UserRoleDto userRoleDto) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0)
                .eq(User::getId, userRoleDto.getUserId()));
        AssertUtils.isNull(user, "用户不存在");

        // 保存用户角色
        List<UserRole> roles = userRoleDto.getRoleIds().stream().map(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(user.getId());
            userRole.setCreated(LocalDateTime.now());
            return userRole;
        }).collect(Collectors.toList());
        userRoleService.deleteByUserId(userRoleDto.getUserId());
        if (!CollectionUtils.isEmpty(roles)) {
            userRoleService.saveBatch(roles);
        }
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0)
                .eq(User::getUserName, userName));
    }

    @Override
    public PageResult<UserDto> pageList(PageQuery<UserQueryDto> query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        List<UserDto> sheepList = baseMapper.list(query);
        PageInfo<UserDto> pageInfo = new PageInfo<>(sheepList);
        return new PageResult<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public void updateStatus(UserStatusDto statusDto) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getDeleted, 0)
                .eq(User::getId, statusDto.getId()));
        AssertUtils.isNull(user, "用户不存在");

        StatusEnums statusEnums = IEnum.parseByCode(statusDto.getStatus(), StatusEnums.class);
        AssertUtils.isNull(statusEnums, "状态值错误");

        User u = new User();
        u.setId(user.getId());
        u.setStatus(statusDto.getStatus());
        u.setModified(LocalDateTime.now());
        userMapper.updateById(u);
    }
}
