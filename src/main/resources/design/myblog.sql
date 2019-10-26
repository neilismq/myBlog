/*
 Navicat Premium Data Transfer

 Source Server         : home
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 26/10/2019 19:00:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_admin
-- ----------------------------
DROP TABLE IF EXISTS `blog_admin`;
CREATE TABLE `blog_admin`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_admin
-- ----------------------------
INSERT INTO `blog_admin` VALUES ('2', 'admin', 'e5455954c7759e2d0ccf1762571c0e68', '2019-11-02 18:59:44');

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章标题',
  `visit_count` int(11) NULL DEFAULT NULL COMMENT '访问数量',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者名称',
  `is_draft` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否是草稿 0-不是，1-是',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章内容',
  `create_time` timestamp(6) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(6) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article` VALUES ('a961dd5dc1274c919baf1c125ea9a67f', '这是新增的文章', 3, '赵志强', '0', 'sdsad\r\n\r\n@###\r\n##1 \r\n\r\n>ssdsds\r\n\r\n![zzq](http://localhost:80/img/2019-10-26/20191026-181535-u=2059216813,3169419971&fm=26&gp=0.jpg)', '2019-10-26 18:16:17.769000', '2019-10-26 18:18:15.116000');

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `article_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章id',
  `tag_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
INSERT INTO `blog_article_tag` VALUES ('2006e9d8d3584ed192f400911109f958', 'a961dd5dc1274c919baf1c125ea9a67f', 'eeb253059d374bbd8ba50baf2756f434');

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论用户id',
  `article_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章id',
  `is_read` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '0-未读 1-已读',
  `create_time` timestamp(6) NULL DEFAULT NULL COMMENT '评论创建时间',
  `pid` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复的哪条评论（id）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment` VALUES ('f71b426db5d74cda9ab23152e1b6f71a', '<p>1611</p>', 'd122d22844904f0a9df00da60e7a1e2c', 'a961dd5dc1274c919baf1c125ea9a67f', '0', '2019-10-26 18:18:52.688000', NULL);

-- ----------------------------
-- Table structure for blog_file
-- ----------------------------
DROP TABLE IF EXISTS `blog_file`;
CREATE TABLE `blog_file`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `origin_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件原名称',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件相对路径',
  `size` int(11) NULL DEFAULT NULL COMMENT '文件大小',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of blog_file
-- ----------------------------
INSERT INTO `blog_file` VALUES ('71b0e681ee6a47348c3672d7241bec34', '20191026-181535-u=2059216813,3169419971&fm=26&gp=0.jpg', 'u=2059216813,3169419971&fm=26&gp=0.jpg', '2019-10-26/20191026-181535-u=2059216813,3169419971&fm=26&gp=0.jpg', 56363, '2019-10-26 18:15:35');

-- ----------------------------
-- Table structure for blog_setting
-- ----------------------------
DROP TABLE IF EXISTS `blog_setting`;
CREATE TABLE `blog_setting`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `app_key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `app_value` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `article_count` int(11) NULL DEFAULT NULL COMMENT '文章数量',
  `create_time` timestamp(6) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
INSERT INTO `blog_tag` VALUES ('eeb253059d374bbd8ba50baf2756f434', '数据结构2', NULL, '2019-10-26 18:13:33.936000');

-- ----------------------------
-- Table structure for blog_user
-- ----------------------------
DROP TABLE IF EXISTS `blog_user`;
CREATE TABLE `blog_user`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `personal_site` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人网址',
  `create_time` timestamp(6) NULL DEFAULT NULL COMMENT '创建或者更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_user
-- ----------------------------
INSERT INTO `blog_user` VALUES ('2c2cfc1a719642048552d350b7c7b8c8', '3232', '32321@qq.com', 'fdsfdsfds', '2019-10-26 18:52:49.166000');
INSERT INTO `blog_user` VALUES ('d122d22844904f0a9df00da60e7a1e2c', '4515', '515@qq.5152', '5616', '2019-10-26 18:18:52.686000');

SET FOREIGN_KEY_CHECKS = 1;
