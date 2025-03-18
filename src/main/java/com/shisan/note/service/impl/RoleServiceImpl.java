package com.shisan.note.service.impl;

import cn.shisan.common.domain.common.PageQuery;
import cn.shisan.common.domain.common.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shisan.note.dto.admin.RoleDto;
import com.shisan.note.dto.admin.RolePermissionDto;
import com.shisan.note.dto.query.RoleQueryDto;
import com.shisan.note.entity.Role;
import com.shisan.note.entity.RolePermission;
import com.shisan.note.mapper.admin.RoleMapper;
import com.shisan.note.service.RolePermissionService;
import com.shisan.note.service.RoleService;
import com.shisan.note.utils.AssertUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;
    private final RolePermissionService rolePermissionService;

    @Override
    public Role findById(Long id) {
        return roleMapper.selectOne(Wrappers.<Role>lambdaQuery()
                .eq(Role::getDeleted, 0)
                .eq(Role::getId, id));
    }

    @Override
    public int insert(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        role.setCreated(LocalDateTime.now());
        role.setModified(LocalDateTime.now());
        return roleMapper.insert(role);
    }

    @Override
    public int update(RoleDto roleDto) {
        Role role = findById(roleDto.getId());
        AssertUtils.isNull(role, "角色不存在");

        Role r = new Role();
        BeanUtils.copyProperties(roleDto, r);
        r.setModified(LocalDateTime.now());
        return roleMapper.updateById(r);
    }

    @Override
    public int delete(Long id) {
        Role role = new Role();
        role.setId(id);
        role.setDeleted(1);
        role.setModified(LocalDateTime.now());
        return roleMapper.updateById(role);
    }

    @Override
    public void setPermission(RolePermissionDto rolePermissionDto) {
        Role role = findById(rolePermissionDto.getRoleId());
        AssertUtils.isNull(role, "角色不存在");

        // 保存角色权限资源
        List<RolePermission> permissions = rolePermissionDto.getPermissionIds().stream().map(o -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(rolePermissionDto.getRoleId());
            rolePermission.setPermissionId(o);
            rolePermission.setCreated(LocalDateTime.now());
            return rolePermission;
        }).collect(Collectors.toList());
        rolePermissionService.deleteByRoleId(rolePermissionDto.getRoleId());
        if (!CollectionUtils.isEmpty(permissions)) {
            rolePermissionService.saveBatch(permissions);
        }
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    public PageResult<Role> pageList(PageQuery<RoleQueryDto> query) {
        RoleQueryDto params = query.getParams();
        PageHelper.startPage(query.getPage(), query.getSize());
        // 查询参数
        List<Role> roleList = list(params);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return new PageResult<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @Override
    public List<Role> list(RoleQueryDto query) {
        // 查询参数
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getDeleted, 0);
        if(null != query.getType()){
            queryWrapper.lambda().eq(Role::getType, query.getType());
        }
        if(StringUtils.isNotBlank(query.getName())){
            queryWrapper.lambda().like(Role::getName, query.getName());
        }
        queryWrapper.lambda().orderByDesc(Role::getCreated);
        return baseMapper.selectList(queryWrapper);
    }

}