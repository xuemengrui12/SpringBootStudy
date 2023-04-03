/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50518
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2019-12-05 15:37:21
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
    `password`  varchar(32) DEFAULT NULL COMMENT '密码',
    `user_sex`  varchar(32) DEFAULT NULL,
    `nick_name` varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8;
