package com.boot.web.wxgroup.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.boot.web.wxgroup.model.WxUserGroup;
import com.boot.web.wxgroup.mapper.WxUserGroupMapper;
import com.boot.util.CommonEntity;
import com.boot.util.ServiceMybatis;


/**
 * 
 * @author 赵祖龙
 * 微信用户分组信息表业务层
 * 2019-09-26
 */
@Service("wxUserGroupService")
public class WxUserGroupService extends ServiceMybatis<WxUserGroup>{

	@Resource
	private WxUserGroupMapper wxUserGroupMapper;
	
	/**
	 * 分页展示(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public PageInfo<CommonEntity> queryPageInfo(Map<String, Object> params) {
		List<CommonEntity> list = null;
		try {
			logger.info("#=================开始分页查询【微信用户分组信息表】数据，带动态权限========================#");
			PageHelper.startPage(params);
			list = wxUserGroupMapper.queryPageInfo(params);
			logger.info("#=================动态权限查询成功！=================================#");
		} catch (Exception e) {
			logger.info("#=================动态权限查询出错，原因如下：========================#");
			logger.info("#                 1、此表没有和机构或者用户相关联的字段                               #");
			logger.info("#                 2、角色配置不正确                                                                     #");
			logger.info("#                 3、SQL本身语法错误                                                                   #");
			logger.info("#=================系统默认处理机制：查询所有数据======================#");
			params.remove("dynamicSQL");
			PageHelper.startPage(params);
			list = wxUserGroupMapper.queryPageInfo(params);
		}
		return new PageInfo<CommonEntity>(list);
	}
	
	/**
	 * 分页展示(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public PageInfo<WxUserGroup> queryPageInfoEntity(Map<String, Object> params) {
		List<WxUserGroup> list = null;
		try {
			logger.info("#=================开始分页查询【微信用户分组信息表】数据，带动态权限========================#");
			PageHelper.startPage(params);
			list = wxUserGroupMapper.entityList(params);
			logger.info("#=================动态权限查询成功！=================================#");
		} catch (Exception e) {
			logger.info("#=================动态权限查询出错，原因如下：========================#");
			logger.info("#                 1、此表没有和机构或者用户相关联的字段                               #");
			logger.info("#                 2、角色配置不正确                                                                     #");
			logger.info("#                 3、SQL本身语法错误                                                                   #");
			logger.info("#=================系统默认处理机制：查询所有数据======================#");
			params.remove("dynamicSQL");
			PageHelper.startPage(params);
			list = wxUserGroupMapper.entityList(params);
		}
		return new PageInfo<WxUserGroup>(list);
	}	
	/**
	 * 列表(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return List<CommonEntity>
	 */
	public List<CommonEntity> commonList(Map<String, Object> params) {
		logger.info("#=================开始根据不同条件查询【微信用户分组信息表】列表数据，返回通用对象========================#");
		List<CommonEntity> list = wxUserGroupMapper.queryPageInfo(params);
		return list;
	}
	
	/**
	 * 列表(可带条件查询)
	 * 返回的是实体list
	 * @param params
	 * @return List<WxUserGroup>
	 */
	public List<WxUserGroup> entityList(Map<String, Object> params) {
		logger.info("#=================开始根据不同条件查询【微信用户分组信息表】列表数据，返回实体对象========================#");
		List<WxUserGroup> list = wxUserGroupMapper.entityList(params);
		return list;
	}
	
	/**
	 * 列表(查询所有，不带条件)
	 * @return
	 */
	public List<WxUserGroup> list() {
		List<WxUserGroup> list = this.selectAll();
		return list;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public WxUserGroup queryById(String id){
		return this.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据不同条件查询一条数据
	 * @param params
	 * @return
	 */
	public WxUserGroup queryOne(Map<String, Object> params){
		logger.info("#=================开始根据不同条件查询一条【微信用户分组信息表】数据，返回实体对象========================#");
		return wxUserGroupMapper.queryOne(params);
	}
	
	/**
	 * 根据不同条件查询一条数据
	 * @param record
	 * @return
	 */
	public WxUserGroup queryOne(WxUserGroup record){
		return this.selectOne(record);
	}
	
	 /**
	 * 查询单条数据,返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public CommonEntity queryOneCommon(Map<String, Object> params){
	logger.info("#=================开始根据不同条件查询一条【微信用户分组信息表】数据，返回通用对象========================#");
		return wxUserGroupMapper.queryOneCommon(params);
	}
	
	/**
	 * 保存操作
	 * @param wxUserGroup
	 * @return
	 */
	public int save(WxUserGroup wxUserGroup){
		return this.insertSelective(wxUserGroup);
	}
	
	/**
	 * 批量保存操作
	 * @param list
	 * @return
	 */
	public int insertBatch(List<WxUserGroup> list){
		return wxUserGroupMapper.insertBatch(list);
	}
	
	/**
	 * 更新操作
	 * @param wxUserGroup
	 * @return
	 */
	public int update(WxUserGroup wxUserGroup){
		return this.updateByPrimaryKeySelective(wxUserGroup);
	}
	/**
	 * 更新操作
	 * @param wxUserGroup
	 * @return
	 */
	public int updateBatch(List<WxUserGroup> list){
		return wxUserGroupMapper.updateBatchEntity(list);
	}	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteWxUserGroup(String id){
		return this.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public Integer deleteWxUserGroup(String[] ids) {
		int count = 0;
		for (String id : ids) {
			count += deleteWxUserGroup(id);
		}
		return count;
	}
	
	/**
	 * 根据不同组合条件删除
	 * @param params
	 * @return
	 */
	public int deleteByParams(Map<String, Object> params){
		logger.info("#=================开始根据不同条件删除【微信用户分组信息表】数据========================#");
		return wxUserGroupMapper.deleteByParams(params);
	}
	
	
}