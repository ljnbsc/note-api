-- ----------------------------
-- Records of tbl_permission
-- ----------------------------
INSERT INTO `tbl_permission` VALUES (1, 0, '系统设置', '/sys', 1, 1, '系统设置', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (2, 0, '羊', '/sheep', 1, 1, '羊', 2, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (3, 1, '权限资源', '/permission', 1, 1, '权限资源', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (4, 1, '用户', '/user', 1, 1, '用户菜单', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (5, 1, '角色管理', '/role', 1, 1, '角色管理', 2, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (6, 3, '添加', '/api/permission/add', 2, 1, '添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (7, 3, '修改', '/api/permission/update', 2, 1, '修改', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (8, 3, '删除', '/api/permission/delete', 2, 1, '删除', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (9, 3, '权限资源查询', '/api/permission/findById/*', 2, 1, '权限资源查询', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (10, 3, '权限资源树查询', '/api/permission/findTree', 2, 1, '权限资源树查询', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (11, 3, '列表', '/api/permission/page', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (12, 3, '根据父类查询资源权限', '/api/permission/findByParentId/*', 2, 1, '根据父类查询资源权限', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (13, 4, '列表', '/api/user/page', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (14, 4, '设置角色', '/api/user/setRole', 2, 1, '设置角色', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (15, 2, '羊种类管理', '/sheep-type', 1, 1, '羊种类管理', 3, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (16, 2, '羊圈管理', '/sheepfold', 1, 1, '羊圈管理', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (17, 2, '羊管理', '/sheep', 1, 1, '羊管理', 2, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (18, 15, '添加', '/api/sheepType/add', 2, 1, '种类添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (19, 15, '修改', '/api/sheepType/update', 2, 1, '种类修改', 2, now(), NULL, 0);
INSERT INTO `tbl_permission` VALUES (20, 15, '详情', '/api/sheepType/details/*', 2, 1, '种类详情', 3, now(), NULL, 0);
INSERT INTO `tbl_permission` VALUES (21, 15, '删除', '/api/sheepType/delete/*', 2, 1, '种类删除', 4, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (22, 15, '列表', '/api/sheepType/page', 2, 1, '种类列表', 5, now(), NULL, 0);
INSERT INTO `tbl_permission` VALUES (23, 15, '全部种类', '/api/sheepType/findAll', 2, 1, '全部种类', 6, now(), NULL, 0);
INSERT INTO `tbl_permission` VALUES (24, 16, '添加', '/api/sheepfold/add', 2, 1, '添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (25, 16, '修改', '/api/sheepfold/update', 2, 1, '修改', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (26, 16, '删除', '/api/sheepfold/delete/*', 2, 1, '删除', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (27, 16, '详情', '/api/sheepfold/details/*', 2, 1, '详情', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (28, 16, '列表', '/api/sheepfold/page', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (29, 17, '添加', '/api/sheep/add', 2, 1, '添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (30, 17, '修改', '/api/sheep/update', 2, 1, '修改', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (31, 17, '删除', '/api/sheep/delete', 2, 1, '删除', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (32, 17, '详情', '/api/sheep/details/*', 2, 1, '详情', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (33, 17, '列表', '/api/sheep/page', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (34, 5, '添加', '/api/role/add', 2, 1, '添加', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (35, 5, '修改', '/api/role/update', 2, 1, '修改', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (36, 5, '删除', '/api/role/delete', 2, 1, '删除', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (37, 5, '设置权限', '/api/role/setPermission', 2, 1, '设置权限', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (38, 5, '获取角色权限集合', '/api/role/findPermissionIds/*', 2, 1, '获取角色权限集合', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (39, 5, '角色查询', '/api/role/findById/*', 2, 1, '角色查询', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (40, 5, '分页列表', '/api/role/page', 2, 1, '分页列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (41, 5, '列表', '/api/role/list', 2, 1, '列表', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (42, 5, '用户角色', '/api/role/findByUserId/*', 2, 1, '用户角色', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (43, 16, '全部羊圈', '/api/sheepfold/findAll', 2, 1, '全部羊圈', 1, now(), null, 0);
INSERT INTO `tbl_permission` VALUES (44, 0, '首页', '/home', 1, 1, '首页', 0, now(), NULL, 0);
INSERT INTO `tbl_permission` VALUES (45, 1, '文件权限', '/api/local/file/*', 2, 1, '文件上传/下载、删除权限', 0, now(), NULL, 0);


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

