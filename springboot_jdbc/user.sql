/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50518
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50518
File Encoding         : 65001

Date: 2020-01-28 15:42:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'sss', 'm');
INSERT INTO `user` VALUES ('2', 'ddd', 'f');
INSERT INTO `user` VALUES ('3', 'test', 'f');
