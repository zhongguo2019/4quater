package com.boot.web.todaywork.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.boot.util.CommonEntity;
import com.boot.web.todaywork.model.DoufuTodayWork;
import com.boot.web.todaywork.mapper.DoufuTodayWorkMapper;



/**
 * 
 * @author zhaozulong
 * 当天工作记录信息表业务层
 * 2019-07-29
 */
@Service("doufuTodayWorkService")
public class DoufuTodayWorkService {

	@Resource
	private DoufuTodayWorkMapper doufuTodayWorkMapper;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 分页展示(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public PageInfo<CommonEntity> queryPageInfo(Map<String, Object> params) {
		List<CommonEntity> list = null;
		try {
			logger.info("#=================开始分页查询【当天工作记录信息表】数据，带动态权限========================#");
			PageHelper.startPage(params);
			list = doufuTodayWorkMapper.queryPageInfo(params);
			logger.info("#=================动态权限查询成功！=================================#");
		} catch (Exception e) {
			logger.info("#=================动态权限查询出错，原因如下：========================#");
			logger.info("#                 1、此表没有和机构或者用户相关联的字段                               #");
			logger.info("#                 2、角色配置不正确                                                                     #");
			logger.info("#                 3、SQL本身语法错误                                                                   #");
			logger.info("#=================系统默认处理机制：查询所有数据======================#");
			params.remove("dynamicSQL");
			PageHelper.startPage(params);
			list = doufuTodayWorkMapper.queryPageInfo(params);
		}
		return new PageInfo<CommonEntity>(list);
	}
	
	/**
	 * 列表(可带条件查询)
	 * 返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return List<CommonEntity>
	 */
	public List<CommonEntity> commonList(Map<String, Object> params) {
		logger.info("#=================开始根据不同条件查询【当天工作记录信息表】列表数据，返回通用对象========================#");
		List<CommonEntity> list = doufuTodayWorkMapper.queryPageInfo(params);
		return list;
	}
	
	/**
	 * 列表(可带条件查询)
	 * 返回的是实体list
	 * @param params
	 * @return List<DoufuTodayWork>
	 */
	public List<DoufuTodayWork> entityList(Map<String, Object> params) {
		logger.info("#=================开始根据不同条件查询【当天工作记录信息表】列表数据，返回实体对象========================#");
		List<DoufuTodayWork> list = doufuTodayWorkMapper.entityList(params);
		return list;
	}
	
	/**
	 * 列表(查询所有，不带条件)
	 * @return
	 */
	public List<DoufuTodayWork> list() {
		List<DoufuTodayWork> list = this.selectAll();
		return list;
	}
	
	/**
	 * 根据主键查询
	 * @param id
	 * @return
	 */
	public DoufuTodayWork queryById(String id){
		return this.selectByPrimaryKey(id);
	}
	
	/**
	 * 根据不同条件查询一条数据
	 * @param params
	 * @return
	 */
	public DoufuTodayWork queryOne(Map<String, Object> params){
		logger.info("#=================开始根据不同条件查询一条【当天工作记录信息表】数据，返回实体对象========================#");
		return doufuTodayWorkMapper.queryOne(params);
	}
	
	/**
	 * 根据不同条件查询一条数据
	 * @param record
	 * @return
	 */
	public DoufuTodayWork queryOne(DoufuTodayWork record){
		return this.selectOne(record);
	}
	
	 /**
	 * 查询单条数据,返回的是通用实体，不受实体属性限制，相当于map
	 * @param params
	 * @return
	 */
	public CommonEntity queryOneCommon(Map<String, Object> params){
	logger.info("#=================开始根据不同条件查询一条【当天工作记录信息表】数据，返回通用对象========================#");
		return doufuTodayWorkMapper.queryOneCommon(params);
	}
	
	/**
	 * 保存操作
	 * @param doufuTodayWork
	 * @return
	 */
	public int save(DoufuTodayWork doufuTodayWork){
		return this.insertSelective(doufuTodayWork);
	}
	
	/**
	 * 批量保存操作
	 * @param list
	 * @return
	 */
	public int insertBatch(List<DoufuTodayWork> list){
		return doufuTodayWorkMapper.insertBatch(list);
	}
	
	/**
	 * 更新操作
	 * @param doufuTodayWork
	 * @return
	 */
	public int update(DoufuTodayWork doufuTodayWork){
		return this.updateByPrimaryKeySelective(doufuTodayWork);
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteDoufuTodayWork(String id){
		return this.deleteByPrimaryKey(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public Integer deleteDoufuTodayWork(String[] ids) {
		int count = 0;
		for (String id : ids) {
			count += deleteDoufuTodayWork(id);
		}
		return count;
	}
	
	/**
	 * 根据不同组合条件删除
	 * @param params
	 * @return
	 */
	public int deleteByParams(Map<String, Object> params){
		logger.info("#=================开始根据不同条件删除【当天工作记录信息表】数据========================#");
		return doufuTodayWorkMapper.deleteByParams(params);
	}
	
	
}