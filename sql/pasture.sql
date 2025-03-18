/*
 Navicat Premium Data Transfer

 Source Server         : mysql-c
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : bj-cynosdbmysql-grp-ehywp1aw.sql.tencentcdb.com:23714
 Source Schema         : note

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 21/06/2024 14:52:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_permission`;
CREATE TABLE `tbl_permission`
(
    `id`        bigint                                                  NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `parent_id` bigint                                                  NULL DEFAULT NULL COMMENT '父ID',
    `name`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '权限名称',
    `url`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url 地址',
    `type`      tinyint(1)                                              NULL DEFAULT NULL COMMENT '类型 1菜单 2api',
    `status`    tinyint(1)                                              NULL DEFAULT NULL COMMENT '状态 1启用 2停用',
    `remark`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '备注',
    `sort`      int                                                     NULL DEFAULT NULL COMMENT '排序',
    `created`   datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `modified`  datetime                                                NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`   tinyint(1)                                              NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '权限表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role`
(
    `id`       bigint                                                  NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '角色名称',
    `type`     tinyint(1)                                              NULL DEFAULT NULL COMMENT '角色类型：0-超级管理员，1-普通角色',
    `remark`   varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
    `created`  datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `modified` datetime                                                NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`  tinyint(1)                                              NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role_permission`;
CREATE TABLE `tbl_role_permission`
(
    `id`            bigint     NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `role_id`       bigint     NULL DEFAULT NULL COMMENT '角色ID',
    `permission_id` bigint     NULL DEFAULT NULL COMMENT '权限ID',
    `created`       datetime   NULL DEFAULT NULL COMMENT '创建时间',
    `modified`      datetime   NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`       tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色权限关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_sheep
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sheep`;
CREATE TABLE `tbl_sheep`
(
    `id`             bigint                                                 NOT NULL AUTO_INCREMENT COMMENT '羊ID',
    `parent_id`      bigint                                                 NOT NULL COMMENT '父ID',
    `sheepfold_id`   bigint                                                 NOT NULL COMMENT '羊圈ID',
    `user_id`        bigint                                                 NOT NULL COMMENT '所属用户ID',
    `type_id`        bigint                                                 NOT NULL COMMENT '羊类型ID',
    `name`           varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
    `code`           varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编号',
    `img`            varchar(5000)                                                DEFAULT NULL COMMENT '图片地址',
    `purchase_price` decimal(10, 2)                                         NULL DEFAULT NULL COMMENT '购入价格',
    `sold_flag`      tinyint(1)                                             NULL DEFAULT 0 COMMENT '是否售出 0否 1是',
    `sales_price`    decimal(10, 2)                                         NULL DEFAULT NULL COMMENT '销售价格',
    `sales_date`     datetime                                               NULL DEFAULT NULL COMMENT '销售日期',
    `birth_date`     datetime                                               NULL DEFAULT NULL COMMENT '出生日期',
    `pregnant_date`  datetime                                               NULL DEFAULT NULL COMMENT '怀孕日期',
    `pregnant_flag`  tinyint(1)                                             NULL DEFAULT 0 COMMENT '是否怀孕 0否 1是',
    `created`        datetime                                               NULL DEFAULT NULL COMMENT '创建时间',
    `modified`       datetime                                               NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`        tinyint(1)                                             NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '羊信息管理表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_sheep_pregnancy
-- ----------------------------
CREATE TABLE `tbl_sheep_pregnancy`
(
    `id`              bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`         bigint NOT NULL COMMENT '所属用户ID',
    `parent_sheep_id` bigint NOT NULL COMMENT '羊父ID',
    `sheep_id`        bigint NOT NULL COMMENT '子羊ID',
    `time`            datetime   DEFAULT NULL COMMENT '怀孕时间',
    `birth_date`      datetime   DEFAULT NULL COMMENT '出生日期',
    `created`         datetime   DEFAULT NULL COMMENT '创建时间',
    `modified`        datetime   DEFAULT NULL COMMENT '修改时间',
    `deleted`         tinyint(1) DEFAULT '0' COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='羊孕期表';

-- ----------------------------
-- Table structure for tbl_sheep_type
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sheep_type`;
CREATE TABLE `tbl_sheep_type`
(
    `id`       bigint                                                  NOT NULL AUTO_INCREMENT COMMENT '主键ID（羊类型ID）',
    `user_id`  bigint                                                  NOT NULL COMMENT '用户id',
    `name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '种类名称',
    `remark`   varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
    `created`  datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `modified` datetime                                                NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`  tinyint(1)                                              NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '羊种类表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_sheepfold
-- ----------------------------
DROP TABLE IF EXISTS `tbl_sheepfold`;
CREATE TABLE `tbl_sheepfold`
(
    `id`       bigint                                                  NOT NULL AUTO_INCREMENT COMMENT '主键ID(羊圈ID)',
    `user_id`  bigint                                                  NULL DEFAULT NULL COMMENT '用户id',
    `name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '羊圈名称',
    `address`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '羊圈位置',
    `remark`   varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
    `created`  datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `modified` datetime                                                NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`  tinyint(1)                                              NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '羊圈管理表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`
(
    `id`        bigint                                                  NOT NULL AUTO_INCREMENT,
    `name`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `phone`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `email`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `status`    int                                                     NULL DEFAULT NULL,
    `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `created`   datetime                                                NULL DEFAULT NULL,
    `modified`  datetime                                                NULL DEFAULT NULL,
    `deleted`   tinyint                                                 NULL DEFAULT 0,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for tbl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_role`;
CREATE TABLE `tbl_user_role`
(
    `id`       bigint     NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`  bigint     NULL DEFAULT NULL COMMENT '用户ID',
    `role_id`  bigint     NULL DEFAULT NULL COMMENT '角色ID',
    `created`  datetime   NULL DEFAULT NULL COMMENT '创建时间',
    `modified` datetime   NULL DEFAULT NULL COMMENT '修改时间',
    `deleted`  tinyint(1) NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `id` (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户角色关联表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
