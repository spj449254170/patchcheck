/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : kingdee_patchcheck

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-02-02 15:27:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('0', '开发部');
INSERT INTO `department` VALUES ('1', '实施部');

-- ----------------------------
-- Table structure for `file`
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file
-- ----------------------------

-- ----------------------------
-- Table structure for `filemas`
-- ----------------------------
DROP TABLE IF EXISTS `filemas`;
CREATE TABLE `filemas` (
  `id` int(11) NOT NULL,
  `createtime` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filemas
-- ----------------------------

-- ----------------------------
-- Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('151');
INSERT INTO `hibernate_sequence` VALUES ('151');
INSERT INTO `hibernate_sequence` VALUES ('151');
INSERT INTO `hibernate_sequence` VALUES ('151');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(255) NOT NULL,
  `closetype` bit(1) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `userid` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of item
-- ----------------------------


-- ----------------------------
-- Table structure for `itemand_user`
-- ----------------------------
DROP TABLE IF EXISTS `itemand_user`;
CREATE TABLE `itemand_user` (
  `id` int(11) NOT NULL,
  `itemid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of itemand_user
-- ----------------------------


-- ----------------------------
-- Table structure for `patch`
-- ----------------------------
DROP TABLE IF EXISTS `patch`;
CREATE TABLE `patch` (
  `id` int(255) NOT NULL,
  `commitman` varchar(255) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `ischeck` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `real_url` varchar(255) DEFAULT NULL,
  `itemid` int(11) DEFAULT NULL,
  `filemasid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patch
-- ----------------------------

-- ----------------------------
-- Table structure for `patchtype`
-- ----------------------------
DROP TABLE IF EXISTS `patchtype`;
CREATE TABLE `patchtype` (
  `id` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `istrue` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `ruler` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patchtype
-- ----------------------------
INSERT INTO `patchtype` VALUES ('44', 'kingdee/eas/server/lib/sp\nkingdee/eas/server/deploy/fileserver.ear/easWebClient/lib/sp', '', 'class', 'java', 'sp{1}_[a-z]+[0-9]*_class{1}');

-- ----------------------------
-- Table structure for `patch_entry`
-- ----------------------------
DROP TABLE IF EXISTS `patch_entry`;
CREATE TABLE `patch_entry` (
  `id` varchar(255) NOT NULL,
  `createtime` date DEFAULT NULL,
  `ischeck` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patchid` varchar(255) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `createuserid` int(11) DEFAULT NULL,
  `istoin` bit(1) DEFAULT NULL,
  `updatetime` date DEFAULT NULL,
  `updateuserid` int(11) DEFAULT NULL,
  `fileid` int(11) DEFAULT NULL,
  `isclose` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patch_entry
-- ----------------------------


-- ----------------------------
-- Table structure for `patch_log`
-- ----------------------------
DROP TABLE IF EXISTS `patch_log`;
CREATE TABLE `patch_log` (
  `id` varchar(255) NOT NULL,
  `createtime` date DEFAULT NULL,
  `patchid` varchar(255) DEFAULT NULL,
  `updateinfo` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of patch_log
-- ----------------------------


-- ----------------------------
-- Table structure for `power`
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('0', '普通用户');
INSERT INTO `power` VALUES ('1', '管理员');

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', '超级管理员');
INSERT INTO `roles` VALUES ('2', '普通用户');

-- ----------------------------
-- Table structure for `roles_user`
-- ----------------------------
DROP TABLE IF EXISTS `roles_user`;
CREATE TABLE `roles_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT '2',
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_user
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(255) NOT NULL,
  `create_time` date DEFAULT NULL,
  `department` int(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('13', '2020-01-15', '0', '张三', '666666', '11111', '1');