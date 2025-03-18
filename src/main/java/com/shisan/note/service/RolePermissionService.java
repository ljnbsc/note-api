package com.shisan.note.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shisan.note.entity.RolePermission;
import java.util.List;

public interface RolePermissionService extends IService<RolePermission> {

	void deleteByRoleId(Long roleId);

	List<RolePermission> findByRoleId(Long roleId);
}