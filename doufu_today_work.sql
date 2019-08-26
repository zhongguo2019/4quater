/*
Navicat MySQL Data Transfer

Source Server         : VM198
Source Server Version : 50722
Source Host           : 192.168.2.198:3306
Source Database       : weeklyplan

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-08-24 17:29:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `doufu_today_work`
-- ----------------------------
DROP TABLE IF EXISTS `doufu_today_work`;
CREATE TABLE `doufu_today_work` (
  `PROJECT_GROUP_ID` varchar(32) DEFAULT NULL COMMENT '所在项目组名称',
  `PROJECT_ID` varchar(32) DEFAULT NULL COMMENT '项目名称',
  `PRODUCT_ID` varchar(32) DEFAULT NULL COMMENT '对应产品',
  `WORK_CONTENTS` varchar(200) DEFAULT NULL COMMENT '工作内容简写',
  `WORK_DETAIL` varchar(200) DEFAULT NULL COMMENT '工作内容详细描述',
  `FINISH_PERCENT` varchar(4) DEFAULT NULL COMMENT '完成比例',
  `DELAY_REASON` varchar(200) DEFAULT NULL COMMENT '延迟原因',
  `ACCORD_YESTERDAY` varchar(32) DEFAULT NULL COMMENT '对应计划',
  `IS_IMPORTANT` varchar(1) DEFAULT NULL COMMENT '是否重要',
  `IS_EMERGENCY` varchar(1) DEFAULT NULL COMMENT '是否紧急',
  `IMPO_LEVEL` varchar(1) DEFAULT NULL COMMENT '重要级别',
  `ID` varchar(50) NOT NULL COMMENT '主键',
  `DEL_FLAG` varchar(1) NOT NULL COMMENT '逻辑删除标记(0.正常，1.删除)',
  `STATUS` varchar(1) NOT NULL COMMENT '状态',
  `INST_ID` varchar(32) NOT NULL COMMENT '机构ID',
  `LOGIN_IP` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `LOGIN_DATE` datetime DEFAULT NULL COMMENT '登录日期',
  `CREATE_BY` varchar(32) NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime NOT NULL COMMENT '创建时间',
  `UPDATE_BY` varchar(32) NOT NULL COMMENT '最近修改人',
  `UPDATE_DATE` datetime NOT NULL COMMENT '最近修改时间',
  `REMARKS` varchar(1000) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='当天工作记录信息表';

-- ----------------------------
-- Records of doufu_today_work
-- ----------------------------
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求2', '与客户沟通，1104统计报送的需求2', '100%', '无延迟', null, '1', '1', null, '164d8aee-78f6-4fd6-9eb1-720c936202d6', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求9', '与客户沟通，1104统计报送的需求9', '100%', '无延迟', null, '1', '1', null, '275f7912-030c-4ec5-b6ca-3b55c03a716b', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求1', '与客户沟通，1104统计报送的需求1', '100%', '无延迟', null, '1', '1', null, '41c7ecad-8b54-4d49-997d-f7469322f1a8', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求6', '与客户沟通，1104统计报送的需求6', '100%', '无延迟', null, '1', '1', null, '4b8e68a6-0421-4aa8-9f2e-e86d270cb03f', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求3', '与客户沟通，1104统计报送的需求3', '100%', '无延迟', null, '1', '1', null, '55ede685-e0cd-48dc-ad4f-d22bbc7511e5', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求7', '与客户沟通，1104统计报送的需求7', '100%', '无延迟', null, '1', '1', null, '5ca0829e-14af-4fd8-af84-eb300388b9ac', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求4', '与客户沟通，1104统计报送的需求4', '100%', '无延迟', null, '1', '1', null, 'beac7a7f-65e9-4d68-94e6-029f3cf608f8', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求11', '与客户沟通，1104统计报送的需求11', '100%', '无延迟', null, '1', '1', null, 'c3cade0f-c0bf-42f1-b139-c8175af18c1b', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求10', '与客户沟通，1104统计报送的需求10', '100%', '无延迟', null, '1', '1', null, 'd7bd35ce-8e11-4d71-a87e-1a78460768f6', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求8', '与客户沟通，1104统计报送的需求8', '100%', '无延迟', null, '1', '1', null, 'e5f2da59-a720-4fd4-95ee-d88c95e9c2c9', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
INSERT INTO `doufu_today_work` VALUES ('12', '12', '1', '与客户沟通，1104统计报送的需求5', '与客户沟通，1104统计报送的需求5', '100%', '无延迟', null, '1', '1', null, 'f002f65a-872f-456c-acca-fbf22ff69b4b', '0', '0', '9999', '0:0:0:0:0:0:0:1', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', 'C860D1BA157E4F6DB1AB261A8E97D94D', '2019-08-24 16:49:28', null);
