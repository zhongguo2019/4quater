/*
Navicat MySQL Data Transfer

Source Server         : local_machine
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-08-09 15:50:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_project`
-- ----------------------------
DROP TABLE IF EXISTS `zt_project`;
CREATE TABLE `zt_project` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `isCat` enum('1','0') NOT NULL DEFAULT '0',
  `catID` mediumint(8) unsigned NOT NULL,
  `type` varchar(20) NOT NULL DEFAULT 'sprint',
  `parent` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `name` varchar(90) NOT NULL,
  `code` varchar(45) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  `days` smallint(5) unsigned NOT NULL,
  `status` varchar(10) NOT NULL,
  `statge` enum('1','2','3','4','5') NOT NULL DEFAULT '1',
  `pri` enum('1','2','3','4') NOT NULL DEFAULT '1',
  `desc` text NOT NULL,
  `openedBy` varchar(30) NOT NULL DEFAULT '',
  `openedDate` int(10) unsigned NOT NULL DEFAULT '0',
  `openedVersion` varchar(20) NOT NULL,
  `closedBy` varchar(30) NOT NULL DEFAULT '',
  `closedDate` int(10) unsigned NOT NULL DEFAULT '0',
  `canceledBy` varchar(30) NOT NULL DEFAULT '',
  `canceledDate` int(10) unsigned NOT NULL DEFAULT '0',
  `PO` varchar(30) NOT NULL DEFAULT '',
  `PM` varchar(30) NOT NULL DEFAULT '',
  `QD` varchar(30) NOT NULL DEFAULT '',
  `RD` varchar(30) NOT NULL DEFAULT '',
  `team` varchar(30) NOT NULL,
  `acl` enum('open','private','custom') NOT NULL DEFAULT 'open',
  `whitelist` text NOT NULL,
  `order` mediumint(8) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `project` (`parent`,`begin`,`end`,`status`,`order`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_project
-- ----------------------------
INSERT INTO `zt_project` VALUES ('1', '0', '0', 'sprint', '0', '2019年江西农信统一监管报送平台升级项目', '2019RPT_UPDATE', '2019-01-01', '2019-12-31', '261', 'wait', '1', '1', '', '', '0', '9.2.1', '', '0', '', '0', '', '', '', '', '江西农信驻场项目组', 'private', '', '5', '0');
INSERT INTO `zt_project` VALUES ('2', '0', '0', 'sprint', '0', '2019年江苏农信统一监管报送平台项目', '2019JSRPTPLT', '2019-01-01', '2019-12-31', '261', 'doing', '1', '1', '1104平台推广', '', '0', '9.2.1', '', '0', '', '0', 'admin', 'admin', 'admin', 'admin', '江苏农信驻场项目组', 'private', '', '10', '0');
INSERT INTO `zt_project` VALUES ('3', '0', '0', 'sprint', '0', '湖南农信ODS项目', 'HNNXODS', '2019-01-01', '2019-12-31', '261', 'wait', '1', '1', '', '', '0', '9.2.1', '', '0', '', '0', '', '', '', '', '湖南农信ODS项目', 'private', '', '15', '0');
INSERT INTO `zt_project` VALUES ('4', '0', '0', 'sprint', '0', '南京银行非现场合规风险管理系统', 'NJ_MAST', '2019-01-01', '2019-09-30', '195', 'wait', '1', '1', '', '', '0', '9.2.1', '', '0', '', '0', '', '', '', '', '南京银行驻场项目组', 'private', '', '20', '0');
