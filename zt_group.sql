/*
Navicat MySQL Data Transfer

Source Server         : local_machine
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-24 12:04:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_group`
-- ----------------------------
DROP TABLE IF EXISTS `zt_group`;
CREATE TABLE `zt_group` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(30) NOT NULL,
  `role` char(30) NOT NULL DEFAULT '',
  `desc` char(255) NOT NULL DEFAULT '',
  `acl` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_group
-- ----------------------------
INSERT INTO `zt_group` VALUES ('1', '管理员', 'admin', '系统管理员', '');
INSERT INTO `zt_group` VALUES ('2', '研发', 'dev', '研发人员', '');
INSERT INTO `zt_group` VALUES ('3', '测试', 'qa', '测试人员', '');
INSERT INTO `zt_group` VALUES ('4', '项目经理', 'pm', '项目经理', '');
INSERT INTO `zt_group` VALUES ('5', '产品经理', 'po', '产品经理', '');
INSERT INTO `zt_group` VALUES ('6', '研发主管', 'td', '研发主管', '');
INSERT INTO `zt_group` VALUES ('7', '产品主管', 'pd', '产品主管', '');
INSERT INTO `zt_group` VALUES ('8', '测试主管', 'qd', '测试主管', '');
INSERT INTO `zt_group` VALUES ('9', '高层管理', 'top', '高层管理', '');
INSERT INTO `zt_group` VALUES ('10', '其他', '', '其他', '');
INSERT INTO `zt_group` VALUES ('11', 'guest', 'guest', 'For guest', '');
INSERT INTO `zt_group` VALUES ('12', '江西农信驻场项目组', '', '', '');
INSERT INTO `zt_group` VALUES ('13', '江苏农信驻场项目组', '', '', '');
INSERT INTO `zt_group` VALUES ('14', '南京银行驻场项目组', '', '', '');
INSERT INTO `zt_group` VALUES ('15', '青海农信驻场项目组', '', '', '');
INSERT INTO `zt_group` VALUES ('16', '广东农信驻场项目组', '', '', '');
INSERT INTO `zt_group` VALUES ('17', '全国运维(北京)项目组', '', '', '');
INSERT INTO `zt_group` VALUES ('18', '湖南农信驻场项目组', '', '', '');
