package com.boot.web.tomorrowplan.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.boot.web.tomorrowplan.model.DoufuTomorrowPlan;
import com.boot.web.tomorrowplan.mapper.DoufuTomorrowPlanMapper;
import com.boot.util.CommonEntity;
import com.boot.util.ServiceMybatis;


/**
 * 
 * @author 赵祖龙
 * 明天工作计划表业务层
 * 2019-08-26
 */
@Service("doufuTomorrowPlanService")
public class DoufuTomorrowPlanService extends ServiceMybatis<DoufuTomorrowPlan>{

	@Resource
	private DoufuTomorrowPlanMapper doufuTomorrowPlanMapper;
	
	/**
	 * 分页展示(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public PageInfo<CommonEntity> queryPageInfo(Map<String, Object> params) {
		List<CommonEntity> list = null;
		try {
			logger.info("#=================开始分页查询【明天工作计划表】数据，带动态权限========================#");
			PageHelper.startPage(params);
			list = doufuTomorrowPlanMapper.queryPageInfo(params);
			logger.info("#=================动态权限查询成功！=================================#");
		} catch (Exception e) {
			logger.info("#=================动态权限查询出错，原因如下：========================#");
			logger.info("#                 1、此表没有和机构或者用户相关联的字段                               #");
			logger.info("#                 2、角色配置不正确                                                                     #");
			logger.info("#                 3、SQL本身语法错误                                                                   #");
			logger.info("#=================系统默认处理机制：查询所有数据======================#");
			params.remove("dynamicSQL");
			PageHelper.startPage(params);
			list = doufuTomorrowPlanMapper.queryPageInfo(params);
		}
		return new PageInfo<CommonEntity>(list);
	}
	
	/**
	 * 分页展示(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public PageInfo<DoufuTomorrowPlan> queryPageInfoEntity(Map<String, Object> params) {
		List<DoufuTomorrowPlan> list = null;
		try {
			logger.info("#=================开始分页查询【明天工作计划表】数据，带动态权限========================#");
			PageHelper.startPage(params);
			list = doufuTomorrowPlanMapper.entityList(params);
			logger.info("#=================动态权限查询成功！=================================#");
		} catch (Exception e) {
			logger.info("#=================动态权限查询出错，原因如下：========================#");
			logger.info("#                 1、此表没有和机构或者用户相关联的字段                               #");
			logger.info("#                 2、角色配置不正确                                                                     #");
			logger.info("#                 3、SQL本身语法错误                                                                   #");
			logger.info("#=================系统默认处理机制：查询所有数据======================#");
			params.remove("dynamicSQL");
			PageHelper.startPage(params);
			list = doufuTomorrowPlanMapper.entityList(params);
		}
		return new PageInfo<DoufuTomorrowPlan>(list);
	}	
	/**
	 * 列表(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return List<CommonEntity>
	 */
	public List<CommonEntity> commonList(Map<String, Object> params) {
		logger.info("#=================开始根据不同条件查询【明天工作计划表】列表数据，返回通用对象========================#");
		List<CommonEntity> list = doufuTomorrowPlanMapper.queryPageInfo(params);
		return list;
	}
	
	/**
	 * 列表(可带条件查询)
	 * 返回的是实体list
	 * @param params
	 * @return List<DoufuTomorrowPlan>
	 */
	public List<DoufuTomorrowPlan> entityList(Map<String, Object> params) {
		logger.info("#=================开始根据不同条件查询【明天工作计划表】列表数据，返回实体对象========================#");
		List<DoufuTomorrowPlan> list = doufuTomorrowPlanMapper.entityList(params);
		return list;
	}
	
	/**
	 * 列表(查询所有，不带条件)
	 * @return
	 */
	public List<DoufuTomorrowPlan> list() {
		List<DoufuTomorrowPlan> list = this.selectAll();
		return list;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public DoufuTomorrowPlan queryById(String id){
		return this.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据不同条件查询一条数据
	 * @param params
	 * @return
	 */
	public DoufuTomorrowPlan queryOne(Map<String, Object> params){
		logger.info("#=================开始根据不同条件查询一条【明天工作计划表】数据，返回实体对象========================#");
		return doufuTomorrowPlanMapper.queryOne(params);
	}
	
	/**
	 * 根据不同条件查询一条数据
	 * @param record
	 * @return
	 */
	public DoufuTomorrowPlan queryOne(DoufuTomorrowPlan record){
		return this.selectOne(record);
	}
	
	 /**
	 * 查询单条数据,返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public CommonEntity queryOneCommon(Map<String, Object> params){
	logger.info("#=================开始根据不同条件查询一条【明天工作计划表】数据，返回通用对象========================#");
		return doufuTomorrowPlanMapper.queryOneCommon(params);
	}
	
	/**
	 * 保存操作
	 * @param doufuTomorrowPlan
	 * @return
	 */
	public int save(DoufuTomorrowPlan doufuTomorrowPlan){
		return this.insertSelective(doufuTomorrowPlan);
	}
	
	/**
	 * 批量保存操作
	 * @param list
	 * @return
	 */
	public int insertBatch(List<DoufuTomorrowPlan> list){
		return doufuTomorrowPlanMapper.insertBatch(list);
	}
	
	/**
	 * 更新操作
	 * @param doufuTomorrowPlan
	 * @return
	 */
	public int update(DoufuTomorrowPlan doufuTomorrowPlan){
		return this.updateByPrimaryKeySelective(doufuTomorrowPlan);
	}
	/**
	 * 更新操作
	 * @param doufuTomorrowPlan
	 * @return
	 */
	public int updateBatch(List<DoufuTomorrowPlan> list){
		return doufuTomorrowPlanMapper.updateBatchEntity(list);
	}	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteDoufuTomorrowPlan(String id){
		return this.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public Integer deleteDoufuTomorrowPlan(String[] ids) {
		int count = 0;
		for (String id : ids) {
			count += deleteDoufuTomorrowPlan(id);
		}
		return count;
	}
	
	/**
	 * 根据不同组合条件删除
	 * @param params
	 * @return
	 */
	public int deleteByParams(Map<String, Object> params){
		logger.info("#=================开始根据不同条件删除【明天工作计划表】数据========================#");
		return doufuTomorrowPlanMapper.deleteByParams(params);
	}
	
	
}