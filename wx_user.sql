/*
Navicat MySQL Data Transfer

Source Server         : 117.50.96.111
Source Server Version : 50727
Source Host           : 117.50.96.111:3306
Source Database       : weeklyplan

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2019-09-17 15:50:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `wx_user`
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `account` varchar(100) DEFAULT NULL COMMENT '帐号',
  `alaisname` varchar(100) DEFAULT NULL COMMENT '别名',
  `duties` varchar(100) DEFAULT NULL COMMENT '职务',
  `dept` varchar(100) DEFAULT NULL COMMENT '部门',
  `sex` varchar(100) DEFAULT NULL COMMENT '性别',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机',
  `phone` varchar(100) DEFAULT NULL COMMENT '座机',
  `email` varchar(100) DEFAULT NULL COMMENT '个人邮箱',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `shortcorname` varchar(100) DEFAULT NULL COMMENT '企业简称',
  `isactive` varchar(100) DEFAULT NULL COMMENT '激活状态',
  `isforbidden` varchar(100) DEFAULT NULL COMMENT '禁用状态',
  `wxplat` varchar(100) DEFAULT NULL COMMENT '微工作台'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业微信用户信息';

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES ('陈威', 'BaiKaiShui', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/HEC技术服务中心', '男', '', '18643272292', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('得英', 'DeYing', '', '', '北京科瑞明软件有限公司', '男', '18600113210', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('樊献泽', 'WanZhang', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/广东农信监管报送驻场运维', '男', '17665443877', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('付豪', 'FuHao', '', '', '北京科瑞明软件有限公司', '男', '17601266609', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('付卫红', 'JiDanQing', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('葛安东', 'AnDong', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/江苏农信监管报送平台推广', '男', '18551719468', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('贡琳', 'GongLin', '', '', '北京科瑞明软件有限公司', '男', '18508480712', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('郭丽利', 'GuoLiLi', '', '', '北京科瑞明软件有限公司', '男', '15389710283', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('郝越', 'HaoYue', '', '', '北京科瑞明软件有限公司', '男', '15771381991', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('黄琬洁', 'HuangWanJie', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/江苏农信监管报送平台推广', '女', '13770650059', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('jack1', '44', '', '咨询顾问', '北京科瑞明软件有限公司', '男', '13986086999', '', '40570463191@qq.com', '', '', '未激活', '', '未关注');
INSERT INTO `wx_user` VALUES ('贾桂林', 'JiaGuiLin', '', '', '北京科瑞明软件有限公司', '男', '15847108608', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('梁hong', 'Liangh', '', '', '北京科瑞明软件有限公司', '女', '13611119303', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('梁正', 'YiJinTianDeMingYi', '', '', '北京科瑞明软件有限公司', '男', '13910128415', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('李广威', 'LiGuangWei', '', '', '北京科瑞明软件有限公司', '男', '15910611584', '', 'liguangwei@krmsoft.com', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('李佳', 'LiJia', '', '', '北京科瑞明软件有限公司', '男', '13404813106', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('凌云', 'XingYun', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/市场部', '男', '13051860888', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('刘宾', 'jax', '', '', '北京科瑞明软件有限公司', '男', '15639208022', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('刘维彪', 'wind', '', '', '北京科瑞明软件有限公司', '男', '18566278383', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('刘兴菊', 'chris', '', '', '北京科瑞明软件有限公司', '男', '17701295520', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('刘岩', 'LiuYan', '', '', '北京科瑞明软件有限公司', '男', '13911713361', '', '', '', '', '已激活', '', '未关注');
INSERT INTO `wx_user` VALUES ('李亚楠', 'LiYaNan', '', '', '北京科瑞明软件有限公司', '女', '13936568067', '', 'liyanan@krmsoft.com', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('马可菠菜', 'MaKeBoCai', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '未关注');
INSERT INTO `wx_user` VALUES ('梅梅', 'MeiMei', '', '', '北京科瑞明软件有限公司', '男', '18600696734', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('曲文凯', 'kevinDianqu', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('上海-张轶', 'ireancezhang', '', '', '北京科瑞明软件有限公司', '男', '13564108897', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('施文昌', 'WuYu', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('苏大洁', 'SuDaJie', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('孙博虎', 'ManMuShanHe', '', '', '北京科瑞明软件有限公司', '男', '17671455329', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('谭斌波', 'TanBinBo', '', '', '北京科瑞明软件有限公司', '男', '13007209215', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('唐玮', 'TangXiaoDou', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/江苏农信监管报送平台推广', '男', '17751450837', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('test', 'test', '', '', '北京科瑞明软件有限公司', '男', '18612513229', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('王超', 'mr.tWang', '', '', '北京科瑞明软件有限公司', '男', '18393919016', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('汪琪', 'EnXin', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/大数据在线分析BI平台', '男', '19852869487', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('汪伟', 'SiBaDaKeSi', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群', '男', '15951916842', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('王勇', 'cd471dcd5f33864e51ea230f37437568', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('武殿辉', 'WuDianHui', '', '', '北京科瑞明软件有限公司', '男', '13104342520', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('武文袈', 'WuErLang', '', '', '北京科瑞明软件有限公司', '男', '13910128417', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('谢婷婷', 'MiaoXiaoJie', '', '', '北京科瑞明软件有限公司', '女', '18811462260', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('邢威', 'XingWei', '', '', '北京科瑞明软件有限公司', '男', '18604715235', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('新疆_岳东', 'Dong', '', '', '北京科瑞明软件有限公司', '男', '18129206393', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('许俊伟', 'XuJunWei', '', '', '北京科瑞明软件有限公司', '男', '18240967815', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('许升', 'XuSheng', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/江苏农信监管报送平台推广', '男', '13818499058', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('杨振宁', 'YangZhenNing', '', '', '北京科瑞明软件有限公司', '男', '13507655291', '', '11353268@qq.com', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('姚旭', 'YaoXu', '', '', '北京科瑞明软件有限公司', '男', '18001355716', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('岳鹏飞', 'YuePengFei', '', '', '北京科瑞明软件有限公司', '男', '17316209389', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('张海强', 'HaiQiang', '', '', '北京科瑞明软件有限公司', '男', '13322449884', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('张慧', 'ZhangHui', '', '', '北京科瑞明软件有限公司', '男', '13311271378', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('张学红', '4b9d4f159091ba93c538ee6e771b6d89', '', '', '北京科瑞明软件有限公司', '男', '13693010401', '', '', '', '', '未激活', '', '未关注');
INSERT INTO `wx_user` VALUES ('赵二喜', 'ZhaoErXi', '', '', '北京科瑞明软件有限公司', '男', '18515590957', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('赵庆新', 'ZhaoQingXin', '', '', '北京科瑞明软件有限公司', '男', '', '', '', '', '', '未激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('赵晓瑞', 'KeDaduck', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/大数据在线分析BI平台', '男', '13770322303', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('赵雅静', 'ZhaoYaJing', '', '', '北京科瑞明软件有限公司', '男', '13261919899', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('赵正', 'ZhaoZheng', '', '', '北京科瑞明软件有限公司;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群;北京科瑞明软件有限公司/工程部/江苏农信驻场项目群/客户风险报送', '男', '17551012117', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('赵祖龙', 'ZhaoZuLong', '', '', '北京科瑞明软件有限公司', '男', '18612790818', '', '', '', '', '已激活', '', '未关注');
INSERT INTO `wx_user` VALUES ('周春阳', 'ZhouChunYang', '', '', '北京科瑞明软件有限公司', '男', '13611580820', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('周凯平', 'ZhouKaiPing', '', '', '北京科瑞明软件有限公司', '女', '17367782306', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('陈丽娥', 'Xiao', '', '', '北京科瑞明软件有限公司/工程部/江西农信监管报送平台V2.0;北京科瑞明软件有限公司/HEC技术服务中心', '男', '15805170693', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('葛风娥', 'FengE', '', '', '北京科瑞明软件有限公司/工程部/江西农信监管报送平台V2.0;北京科瑞明软件有限公司/HEC技术服务中心', '女', '18970388380', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('刘飞', 'LiuFei', '', '', '北京科瑞明软件有限公司/工程部/江西农信监管报送平台V2.0', '男', '18379133956', '', 'liufei@krmsoft.com', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('许春林', 'bechalin', '', '', '北京科瑞明软件有限公司/工程部/江西农信监管报送平台V2.0;北京科瑞明软件有限公司/HEC技术服务中心', '男', '15170403215', '', '994830304@qq.com', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('杨华', 'YangYang', '', '', '北京科瑞明软件有限公司/工程部/江西农信监管报送平台V2.0', '男', '15179168525', '', '', '', '', '已激活', '', '已关注');
INSERT INTO `wx_user` VALUES ('周志勇', 'ZhouSanGe', '', '', '北京科瑞明软件有限公司/工程部/江西农信监管报送平台V2.0', '男', '18907968151', '', '', '', '', '已激活', '', '已关注');
