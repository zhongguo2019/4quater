/*
Navicat MySQL Data Transfer

Source Server         : VM198
Source Server Version : 50722
Source Host           : 192.168.2.198:3306
Source Database       : weeklyplan

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-07-24 11:04:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_happy_work_content`
-- ----------------------------
DROP TABLE IF EXISTS `t_happy_work_content`;
CREATE TABLE `t_happy_work_content` (
  `id` varchar(50) NOT NULL,
  `work_content` varchar(4000) DEFAULT NULL,
  `title_id` varchar(50) DEFAULT NULL,
  `vorder` int(11) DEFAULT NULL,
  `emergency` int(11) DEFAULT NULL,
  `importance` int(11) DEFAULT NULL,
  `doning` int(11) DEFAULT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `systemmaintenance` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_happy_work_content
-- ----------------------------
INSERT INTO `t_happy_work_content` VALUES ('543f11de-2dd7-464e-8605-7988c3587a02', 'dfas', '2b342a9b-ec05-4f21-bba3-e26b0f48f96f', null, '1', '1', '1', null, '4490484c-1ee6-46eb-b229-cf7bb9ea4d71');
INSERT INTO `t_happy_work_content` VALUES ('ae2541ec-de1b-4bc7-aba7-39c5cf46cd6a', '测试集成后的效果', 'e3fdfa36-e9b8-4ff5-ac0f-365912762735', null, '1', '1', '1', null, '4490484c-1ee6-46eb-b229-cf7bb9ea4d71');
INSERT INTO `t_happy_work_content` VALUES ('ba56f322-0bd0-415a-a1ad-4c741e8b8c7e', '工作内容一，加班加点', '2b342a9b-ec05-4f21-bba3-e26b0f48f96f', null, '1', '1', '1', null, '4490484c-1ee6-46eb-b229-cf7bb9ea4d71');
