-- ----------------------------
-- Records of tbl_permission
-- ----------------------------
INSERT INTO `tbl_permission` VALUES (1, 0, '系统设置', '/sys', 1, 1, '系统设置', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (2, 1, '权限资源', '/permission', 1, 1, '权限资源', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (4, 1, '用户', '/user', 1, 1, '用户菜单', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (5, 1, '角色管理', '/role', 1, 1, '角色管理', 2, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (6, 2, '添加', '/api/permission/add', 2, 1, '添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (7, 2, '修改', '/api/permission/update', 2, 1, '修改', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (8, 2, '删除', '/api/permission/delete', 2, 1, '删除', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (9, 2, '权限资源查询', '/api/permission/findById/*', 2, 1, '权限资源查询', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (10, 2, '权限资源树查询', '/api/permission/findTree', 2, 1, '权限资源树查询', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (11, 2, '列表', '/api/permission/page', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (12, 2, '根据父类查询资源权限', '/api/permission/findByParentId/*', 2, 1, '根据父类查询资源权限', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (13, 4, '列表', '/api/user/page', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (14, 4, '设置角色', '/api/user/setRole', 2, 1, '设置角色', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (15, 5, '添加', '/api/role/add', 2, 1, '添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (16, 5, '修改', '/api/role/update', 2, 1, '修改', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (17, 5, '删除', '/api/role/delete', 2, 1, '删除', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (18, 5, '设置权限', '/api/role/setPermission', 2, 1, '设置权限', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (19, 5, '获取角色权限集合', '/api/role/findPermissionIds/*', 2, 1, '获取角色权限集合', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (20, 5, '角色查询', '/api/role/findById/*', 2, 1, '角色查询', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (21, 5, '分页列表', '/api/role/page', 2, 1, '分页列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (22, 5, '列表', '/api/role/list', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (23, 5, '用户角色', '/api/role/findByUserId/*', 2, 1, '用户角色', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (24, 0, '首页', '/home', 1, 1, '首页', 0, now(), NULL, 0);
INSERT INTO `tbl_permission` VALUES (25, 1, '文件权限', '/api/local/file/*', 2, 1, '文件上传/下载、删除权限', 0, now(), NULL, 0);


-- ----------------------------
-- Records of tbl_role
-- ----------------------------
INSERT INTO `tbl_role` VALUES (1, '超级管理员', 0, '此角色拥有所有权限', now(), null, 0);
INSERT INTO `tbl_role` VALUES (2, '管理员', 1, '管理员', now(), null, 0);

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user`
VALUES( 1, '十三', 'shisan', '$2a$10$97GcmnokP1Ajeza12HBOMekhMQH9Ikcy/qVX593e9DR9gIQx0bj.u', '17600506416', '', 1, NULL, now(), NULL, 0 );

-- ----------------------------
-- Records of tbl_user_role
-- ----------------------------
INSERT INTO `tbl_user_role` VALUES (1, 1, 1, now(), null, 0);

