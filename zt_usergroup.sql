/*
Navicat MySQL Data Transfer

Source Server         : local_machine
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-24 12:04:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_usergroup`
-- ----------------------------
DROP TABLE IF EXISTS `zt_usergroup`;
CREATE TABLE `zt_usergroup` (
  `account` char(30) NOT NULL DEFAULT '',
  `group` mediumint(8) unsigned NOT NULL DEFAULT '0',
  UNIQUE KEY `account` (`account`,`group`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_usergroup
-- ----------------------------
INSERT INTO `zt_usergroup` VALUES ('chengshixi', '13');
INSERT INTO `zt_usergroup` VALUES ('chenlie', '12');
INSERT INTO `zt_usergroup` VALUES ('deying', '9');
INSERT INTO `zt_usergroup` VALUES ('geandong', '13');
INSERT INTO `zt_usergroup` VALUES ('gefenge', '12');
INSERT INTO `zt_usergroup` VALUES ('guanbo', '14');
INSERT INTO `zt_usergroup` VALUES ('huangjianhong', '16');
INSERT INTO `zt_usergroup` VALUES ('huangwanjie', '13');
INSERT INTO `zt_usergroup` VALUES ('hujiajie', '18');
INSERT INTO `zt_usergroup` VALUES ('lenggaoxiang', '18');
INSERT INTO `zt_usergroup` VALUES ('ligaoling', '2');
INSERT INTO `zt_usergroup` VALUES ('ligaoling', '14');
INSERT INTO `zt_usergroup` VALUES ('lijunxiang', '2');
INSERT INTO `zt_usergroup` VALUES ('lijunxiang', '14');
INSERT INTO `zt_usergroup` VALUES ('liufei', '12');
INSERT INTO `zt_usergroup` VALUES ('liyongshuai', '2');
INSERT INTO `zt_usergroup` VALUES ('liyongshuai', '14');
INSERT INTO `zt_usergroup` VALUES ('liyuqing', '13');
INSERT INTO `zt_usergroup` VALUES ('luchanggao', '18');
INSERT INTO `zt_usergroup` VALUES ('qiuyi', '18');
INSERT INTO `zt_usergroup` VALUES ('quwenkai', '2');
INSERT INTO `zt_usergroup` VALUES ('tanchao', '18');
INSERT INTO `zt_usergroup` VALUES ('tangkehan', '14');
INSERT INTO `zt_usergroup` VALUES ('tanpeipei', '18');
INSERT INTO `zt_usergroup` VALUES ('wangwei', '13');
INSERT INTO `zt_usergroup` VALUES ('wangyong', '16');
INSERT INTO `zt_usergroup` VALUES ('xuchunlin', '4');
INSERT INTO `zt_usergroup` VALUES ('xuchunlin', '12');
INSERT INTO `zt_usergroup` VALUES ('xusheng', '13');
INSERT INTO `zt_usergroup` VALUES ('yibin', '18');
INSERT INTO `zt_usergroup` VALUES ('zhangximeng', '14');
INSERT INTO `zt_usergroup` VALUES ('zhaoerxi', '4');
INSERT INTO `zt_usergroup` VALUES ('zhaoerxi', '14');
INSERT INTO `zt_usergroup` VALUES ('zhaozheng', '13');
INSERT INTO `zt_usergroup` VALUES ('zhaozulong', '9');
INSERT INTO `zt_usergroup` VALUES ('zhouchunyang', '13');
INSERT INTO `zt_usergroup` VALUES ('zhoulei', '18');
INSERT INTO `zt_usergroup` VALUES ('zhouzhiyong', '12');
