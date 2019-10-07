/*
Navicat MySQL Data Transfer

Source Server         : VM198
Source Server Version : 50722
Source Host           : 192.168.2.198:3306
Source Database       : weeklyplan

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-09-17 08:05:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `wx_user_group`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_group`;
CREATE TABLE `wx_user_group` (
  `USER_ID` varchar(200) NOT NULL COMMENT '小组负责人',
  `USER_CODE` varchar(200) NOT NULL COMMENT '用户编码',
  `USERNAME` varchar(200) NOT NULL COMMENT '用户名称',
  `GROUP_CODE` varchar(200) NOT NULL COMMENT '用户分组编码',
  `GROUP_CNAME` varchar(200) NOT NULL COMMENT '负责小组名称 广东农信;南京银行MAST;',
  `is_msg` varchar(2) DEFAULT '1' COMMENT '是否要通知微信消息',
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `DEL_FLAG` varchar(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记(0.正常，1.删除)',
  `STATUS` varchar(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `INST_ID` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '登录日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '最近修改时间',
  `REMARKS` varchar(1000) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信用户分组信息表';

-- ----------------------------
-- Records of wx_user_group
-- ----------------------------
INSERT INTO `wx_user_group` VALUES ('99999932', 'SiBaDaKeSi', '汪伟', 'JSNX', '江苏农信驻场项目群', '1', '1', '0', '1', null, null, null, null, null, null, null, null);
INSERT INTO `wx_user_group` VALUES ('99999947', 'ZhaoErXi', '赵二喜', 'GDNX|NJYH', '广东农信监管报送驻场运维|南京银行MAST项目组', '1', '2', '0', '1', null, null, null, null, null, null, null, null);
INSERT INTO `wx_user_group` VALUES ('99999955', 'bechalin', '许春林', 'JXNX', '江西农信监管报送平台V2.0', '1', '3', '0', '1', null, null, null, null, null, null, null, null);
