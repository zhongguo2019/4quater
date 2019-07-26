/*
Navicat MySQL Data Transfer

Source Server         : local_machine
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-24 12:02:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_product`
-- ----------------------------
DROP TABLE IF EXISTS `zt_product`;
CREATE TABLE `zt_product` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(90) NOT NULL,
  `code` varchar(45) NOT NULL,
  `type` varchar(30) NOT NULL DEFAULT 'normal',
  `status` varchar(30) NOT NULL DEFAULT '',
  `desc` text NOT NULL,
  `PO` varchar(30) NOT NULL,
  `QD` varchar(30) NOT NULL,
  `RD` varchar(30) NOT NULL,
  `acl` enum('open','private','custom') NOT NULL DEFAULT 'open',
  `whitelist` text NOT NULL,
  `createdBy` varchar(30) NOT NULL,
  `createdDate` datetime NOT NULL,
  `createdVersion` varchar(20) NOT NULL,
  `order` mediumint(8) unsigned NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order` (`order`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_product
-- ----------------------------
INSERT INTO `zt_product` VALUES ('1', '江西农信统一监管报送平台', 'JXNX_RPT', 'normal', 'normal', '', 'admin', 'admin', 'admin', 'open', '', 'admin', '2019-06-04 17:02:36', '9.2.1', '5', '0');
INSERT INTO `zt_product` VALUES ('2', '江苏农信统一监管报送平台', 'JSNXRPT', 'normal', 'normal', '', 'admin', 'admin', 'admin', 'open', '', 'admin', '2019-06-04 17:03:05', '9.2.1', '10', '0');
INSERT INTO `zt_product` VALUES ('3', '电子银行数据分析系统', 'JSNXDSYH', 'normal', 'normal', '', 'admin', 'admin', 'wangwei', 'open', '', 'admin', '2019-06-06 10:50:30', '9.2.1', '15', '0');
INSERT INTO `zt_product` VALUES ('4', '南京银行非现场合规预警管理系统', 'NJ_MAST', 'normal', 'normal', '', 'admin', 'admin', 'admin', 'open', '', 'admin', '2019-06-10 11:21:59', '9.2.1', '20', '0');
