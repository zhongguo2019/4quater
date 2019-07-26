/*
Navicat MySQL Data Transfer

Source Server         : local_machine
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : zentao

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-07-24 12:03:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `zt_module`
-- ----------------------------
DROP TABLE IF EXISTS `zt_module`;
CREATE TABLE `zt_module` (
  `id` mediumint(8) unsigned NOT NULL AUTO_INCREMENT,
  `root` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `branch` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `name` char(60) NOT NULL DEFAULT '',
  `parent` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `path` char(255) NOT NULL DEFAULT '',
  `grade` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `order` smallint(5) unsigned NOT NULL DEFAULT '0',
  `type` char(30) NOT NULL,
  `owner` varchar(30) NOT NULL,
  `short` varchar(30) NOT NULL,
  `deleted` enum('0','1') NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `module` (`root`,`type`,`path`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zt_module
-- ----------------------------
INSERT INTO `zt_module` VALUES ('1', '1', '0', '1104及人行统计报送子系统', '0', ',1,', '1', '10', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('2', '1', '0', '人行支付报送子系统', '0', ',2,', '1', '20', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('3', '1', '0', '银监客户风险报送子系统', '0', ',3,', '1', '30', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('4', '1', '0', '人行标准化送子系统', '0', ',4,', '1', '40', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('5', '1', '0', '非居民涉税信息报送', '0', ',5,', '1', '50', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('6', '1', '0', '业务报表管理系统', '0', ',6,', '1', '60', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('7', '1', '0', '银行卡境外交易', '0', ',7,', '1', '70', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('8', '1', '0', '统一调度数据加工平台', '0', ',8,', '1', '80', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('9', '1', '0', '报文下载工具', '0', ',9,', '1', '90', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('10', '1', '0', '监管集市跑批与短信接口', '0', ',10,', '1', '100', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('11', '1', '0', '中债理财业务', '0', ',11,', '1', '110', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('12', '1', '0', '人行金融消费者投诉', '0', ',12,', '1', '120', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('13', '1', '0', '人行现金统计', '0', ',13,', '1', '130', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('14', '1', '0', '信贷小微客户档案', '0', ',14,', '1', '140', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('15', '1', '0', '取款助农信息', '0', ',15,', '1', '150', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('16', '1', '0', '外管局_金融机构外汇业务', '0', ',16,', '1', '160', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('17', '1', '0', '监管报送核验', '0', ',17,', '1', '170', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('18', '2', '0', '电子银行报表分析系统', '0', ',18,', '1', '10', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('19', '2', '0', 'EAST监管报送', '0', ',19,', '1', '20', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('20', '2', '0', '金标报送系统', '0', ',20,', '1', '30', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('21', '2', '0', '1104报送系统', '0', ',21,', '1', '40', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('22', '2', '0', '人行统计报送系统', '0', ',22,', '1', '50', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('23', '2', '0', '客户风险报送系统', '0', ',23,', '1', '60', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('24', '2', '0', '行内报表分析系统（BI)', '0', ',24,', '1', '70', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('25', '4', '0', '管理驾驶舱', '0', ',25,', '1', '10', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('26', '4', '0', '模型管理__模型探索', '0', ',26,', '1', '20', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('27', '4', '0', '模型管理__模型创建', '0', ',27,', '1', '30', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('28', '4', '0', '模型管理__模型验证', '0', ',28,', '1', '40', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('29', '4', '0', '模型管理__模型审批', '0', ',29,', '1', '50', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('30', '4', '0', '模型管理__模型投产', '0', ',30,', '1', '60', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('31', '4', '0', '模型管理__模型运行', '0', ',31,', '1', '70', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('32', '4', '0', '模型管理__模型监控', '0', ',32,', '1', '80', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('33', '4', '0', '监测预警__预警总览', '0', ',33,', '1', '90', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('34', '4', '0', '监测预警__高频预警', '0', ',34,', '1', '100', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('35', '4', '0', '监测预警__专项预警', '0', ',35,', '1', '110', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('36', '4', '0', '监测预警__灵活预警', '0', ',36,', '1', '120', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('37', '4', '0', '预警处理__发送通知书', '0', ',37,', '1', '130', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('38', '4', '0', '预警处理__接收通知书', '0', ',38,', '1', '140', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('39', '4', '0', '预警处理__核查处置', '0', ',39,', '1', '150', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('40', '4', '0', '预警处理__核查审批', '0', ',40,', '1', '160', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('41', '4', '0', '预警处理__已办事项记录', '0', ',41,', '1', '170', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('42', '4', '0', '预警处理__督办管理', '0', ',42,', '1', '180', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('43', '4', '0', '预警处理__转办记录', '0', ',43,', '1', '190', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('44', '4', '0', '问题管理__已核查台账', '0', ',44,', '1', '200', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('45', '4', '0', '查询查证__源系统查询', '0', ',45,', '1', '210', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('46', '4', '0', '查询查证__基础表查询', '0', ',46,', '1', '220', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('47', '4', '0', '查询查证__客户信息查询', '0', ',47,', '1', '230', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('48', '4', '0', '统计分析__报表配置', '0', ',48,', '1', '240', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('49', '4', '0', '统计分析__统计分析', '0', ',49,', '1', '250', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('50', '4', '0', '系统管理__综合管理', '0', ',50,', '1', '260', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('51', '4', '0', '系统管理__模型配置', '0', ',51,', '1', '270', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('52', '4', '0', '系统管理__日志管理', '0', ',52,', '1', '280', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('53', '4', '0', '系统管理__辅助配置', '0', ',53,', '1', '290', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('54', '4', '0', '系统管理__Smartbi配置', '0', ',54,', '1', '300', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('55', '4', '0', '流程管理__流程模型', '0', ',55,', '1', '310', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('56', '4', '0', '流程管理__流程部署管理', '0', ',56,', '1', '320', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('57', '4', '0', '流程管理__流程定义', '0', ',57,', '1', '330', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('58', '4', '0', '流程管理__流程实例管理', '0', ',58,', '1', '340', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('59', '4', '0', '流程管理__流程审批', '0', ',59,', '1', '350', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('60', '4', '0', '流程管理__流程分组维护', '0', ',60,', '1', '360', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('61', '4', '0', '模型开发', '0', ',61,', '1', '370', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('62', '4', '0', '模型跑批', '0', ',62,', '1', '380', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('63', '4', '0', '查询查证', '0', ',63,', '1', '390', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('64', '4', '0', '自主分析', '0', ',64,', '1', '400', 'story', '', '', '0');
INSERT INTO `zt_module` VALUES ('65', '4', '0', '系统管理_公告管理', '0', ',65,', '1', '410', 'story', '', '', '0');
