/*
Navicat MySQL Data Transfer

Source Server         : VM198
Source Server Version : 50722
Source Host           : 192.168.2.198:3306
Source Database       : weeklyplan

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-07-24 11:04:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `ORGAN_ID` varchar(32) NOT NULL COMMENT '机构号',
  `DEPT_ID` varchar(32) NOT NULL COMMENT '部门',
  `USERNAME` varchar(200) NOT NULL COMMENT '登录名',
  `PASSWORD` varchar(200) NOT NULL COMMENT '密码',
  `NO` varchar(200) DEFAULT NULL COMMENT '工号',
  `NAME` varchar(200) NOT NULL COMMENT '姓名',
  `EMAIL` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(200) DEFAULT NULL COMMENT '电话',
  `MOBILE` varchar(200) DEFAULT NULL COMMENT '手机',
  `USER_TYPE` varchar(1) DEFAULT NULL COMMENT '用户类型',
  `LOGIN_IP` varchar(200) DEFAULT NULL COMMENT '登录IP',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '登录日期',
  `CREATE_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `UPDATE_DATE` datetime DEFAULT NULL COMMENT '最近修改时间',
  `REMARKS` varchar(200) DEFAULT NULL COMMENT '描述',
  `DEL_FLAG` varchar(1) DEFAULT '0' COMMENT '逻辑删除标记(0.正常，1.删除)',
  `STATUS` varchar(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '55555', '', 'admin', '1f82c942befda29b6ed487a51da199f78fce7f05', '0001', '超级管理员', '', '', '18612790818', '1', '0:0:0:0:0:0:0:1', '2019-06-18 17:30:34', '1', '2017-12-02 21:49:36', '1', '2019-07-04 13:56:47', '管理员', '0', '0');
INSERT INTO `sys_user` VALUES ('FB0E8776BFF44749B309EAEEA827CA4F', 'XMGC_001', '', 'zhaozulong', '4ab39c8eef360fcc9d46802ba19a129f', '080015', '赵祖龙', '', '', '', '2', null, null, '1', '2019-07-04 17:01:58', null, '2019-07-04 17:01:58', '', '0', '1');
