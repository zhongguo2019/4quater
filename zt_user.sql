/*
Navicat MySQL Data Transfer

Source Server         : local_machine
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-24 12:07:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_user`
-- ----------------------------
DROP TABLE IF EXISTS `zt_user`;
CREATE TABLE `zt_user` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `dept` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `account` char(30) NOT NULL DEFAULT '',
  `password` char(32) NOT NULL DEFAULT '',
  `role` char(10) NOT NULL DEFAULT '',
  `realname` char(30) NOT NULL DEFAULT '',
  `nickname` char(60) NOT NULL DEFAULT '',
  `commiter` varchar(100) NOT NULL,
  `avatar` char(30) NOT NULL DEFAULT '',
  `birthday` date NOT NULL DEFAULT '0000-00-00',
  `gender` enum('f','m') NOT NULL DEFAULT 'f',
  `email` char(90) NOT NULL DEFAULT '',
  `skype` char(90) NOT NULL DEFAULT '',
  `qq` char(20) NOT NULL DEFAULT '',
  `yahoo` char(90) NOT NULL DEFAULT '',
  `gtalk` char(90) NOT NULL DEFAULT '',
  `wangwang` char(90) NOT NULL DEFAULT '',
  `mobile` char(11) NOT NULL DEFAULT '',
  `phone` char(20) NOT NULL DEFAULT '',
  `address` char(120) NOT NULL DEFAULT '',
  `zipcode` char(10) NOT NULL DEFAULT '',
  `join` date NOT NULL DEFAULT '0000-00-00',
  `visits` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `ip` char(15) NOT NULL DEFAULT '',
  `last` int(10) unsigned NOT NULL DEFAULT '0',
  `fails` tinyint(5) NOT NULL DEFAULT '0',
  `locked` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ranzhi` char(30) NOT NULL DEFAULT '',
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  KEY `user` (`dept`,`email`,`commiter`)
) ENGINE=MyISAM AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_user
-- ----------------------------
INSERT INTO `zt_user` VALUES ('1', '0', 'admin', 'fe80915b2ed2e08aa49c90a1f4bab9b8', '', 'admin', '', '', '', '0000-00-00', 'f', '', '', '', '', '', '', '', '', '', '', '0000-00-00', '16', '172.17.0.1', '1562828350', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('2', '2', 'xuchunlin', '7d7c904cf950cc62f1ab0a0084194134', 'pm', '许春林', '', '', '', '0000-00-00', 'm', 'xuchunlin@krmsoft.com', '', '', '', '', '', '', '', '', '', '2018-04-16', '27', '172.17.0.1', '1562740298', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('3', '2', 'liufei', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '刘飞', '', '', '', '0000-00-00', 'f', 'liufei@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '17', '172.17.0.1', '1562740163', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('4', '2', 'gefenge', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '葛凤娥', '', '', '', '0000-00-00', 'f', 'gefenge@krmsoft.com', '', '', '', '', '', '', '', '', '', '2016-04-01', '17', '172.17.0.1', '1562740026', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('5', '2', 'zhouzhiyong', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '周志勇', '', '', '', '0000-00-00', 'm', 'zhouzhiyong@krmsoft.com', '', '', '', '', '', '', '', '', '', '2014-12-09', '17', '172.17.0.1', '1562742485', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('6', '2', 'chenlie', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '陈丽娥', '', '', '', '0000-00-00', 'f', 'chenlie@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-04-27', '18', '172.17.0.1', '1562893269', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('7', '0', 'zhaozulong', '7d7c904cf950cc62f1ab0a0084194134', 'top', '赵祖龙', '', '', '', '0000-00-00', 'm', 'zhaozulong@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('8', '1', 'geandong', '7d7c904cf950cc62f1ab0a0084194134', 'teamleader', '葛安东', '', '', '', '0000-00-00', 'm', 'geandong@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('9', '1', 'wangwei', '7d7c904cf950cc62f1ab0a0084194134', 'pm', '汪伟', '', '', '', '0000-00-00', 'm', 'wangwei@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '8', '172.17.0.1', '1562842176', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('10', '1', 'zhouchunyang', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '周春阳', '', '', '', '0000-00-00', 'm', 'zhouchunyang@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('11', '1', 'huangwanjie', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '黄琬洁', '', '', '', '0000-00-00', 'f', 'huangwanjie@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '2', '172.17.0.1', '1562755628', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('12', '1', 'zhaozheng', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '赵正', '', '', '', '0000-00-00', 'm', 'zhaozheng@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '1', '172.17.0.1', '1560851439', '2', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('13', '1', 'xusheng', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '许升', '', '', '', '0000-00-00', 'm', 'xusheng@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '2', '172.17.0.1', '1562581706', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('14', '1', 'liyuqing', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '李玉庆', '', '', '', '0000-00-00', 'm', 'liyuqing@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('15', '1', 'chengshixi', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '程诗茜', '', '', '', '0000-00-00', 'm', 'chengshixi@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '1', '172.17.0.1', '1562839344', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('16', '4', 'deying', '7d7c904cf950cc62f1ab0a0084194134', 'top', '得英', '', '', '', '0000-00-00', 'm', 'deying@krmsoft.com', '', '', '', '', '', '', '', '', '', '2009-10-10', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('17', '4', 'zhoulei', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '周磊', '', '', '', '0000-00-00', 'm', 'zhoulei@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('18', '4', 'hujiajie', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '胡家杰', '', '', '', '0000-00-00', 'm', 'hujiajie@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('19', '4', 'lenggaoxiang', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '冷高翔', '', '', '', '0000-00-00', 'm', 'lenggaoxiang@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('20', '4', 'yibin', '7d7c904cf950cc62f1ab0a0084194134', 'pm', '易斌', '', '', '', '0000-00-00', 'm', 'yibin@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('21', '4', 'tanchao', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '谭超', '', '', '', '0000-00-00', 'm', 'tanchao@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('22', '4', 'luchanggao', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '卢昌高', '', '', '', '0000-00-00', 'm', 'luchanggao@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('23', '4', 'qiuyi', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '邱怡', '', '', '', '0000-00-00', 'm', 'qiuyi@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('24', '4', 'tanpeipei', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '谭培培', '', '', '', '0000-00-00', 'm', 'tanpeipei@krmsoft.com', '', '', '', '', '', '', '', '', '', '0000-00-00', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('25', '7', 'zhaoerxi', '7d7c904cf950cc62f1ab0a0084194134', 'pm', '赵二喜', '', '', '', '0000-00-00', 'm', 'zhaoerxi@krmsot.com', '', '', '', '', '', '', '', '', '', '2010-11-16', '7', '172.17.0.1', '1562914730', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('26', '7', 'lijunxiang', '7d7c904cf950cc62f1ab0a0084194134', 'dev', '李骏翔', '', '', '', '0000-00-00', 'm', 'lijunxiang@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '3', '172.17.0.1', '1562914846', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('27', '7', 'ligaoling', '7d7c904cf950cc62f1ab0a0084194134', 'dev', '李高岭', '', '', '', '0000-00-00', 'm', 'ligaoling@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-11', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('28', '7', 'tangkehan', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '唐可焓', '', '', '', '0000-00-00', 'm', '', '', '', '', '', '', '', '', '', '', '2019-06-10', '2', '172.17.0.1', '1562834462', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('29', '7', 'zhangximeng', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '张芯萌', '', '', '', '0000-00-00', 'f', 'zhangxinmeng@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('30', '7', 'guanbo', '7d7c904cf950cc62f1ab0a0084194134', 'teamleader', '关博', '', '', '', '0000-00-00', 'm', 'guanbo@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('31', '7', 'liyongshuai', '7d7c904cf950cc62f1ab0a0084194134', 'dev', '李永帅', '', '', '', '0000-00-00', 'm', 'liyongshuai@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '2', '172.17.0.1', '1562914901', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('32', '3', 'wangyong', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '王勇', '', '', '', '0000-00-00', 'm', 'wangyong@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('33', '3', 'huangjianhong', '7d7c904cf950cc62f1ab0a0084194134', 'imp', '黄剑洪', '', '', '', '0000-00-00', 'm', 'huangjianhong@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
INSERT INTO `zt_user` VALUES ('34', '3', 'quwenkai', '7d7c904cf950cc62f1ab0a0084194134', 'dev', '曲文凯', '', '', '', '0000-00-00', 'm', 'quwenkai@krmsoft.com', '', '', '', '', '', '', '', '', '', '2019-06-10', '0', '', '0', '0', '0000-00-00 00:00:00', '', '0');
