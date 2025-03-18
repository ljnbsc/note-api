package com.shisan.note.mapper.admin;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shisan.note.entity.Role;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findByUserId(Long userId);

}

