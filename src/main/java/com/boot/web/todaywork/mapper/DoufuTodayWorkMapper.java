package com.boot.web.todaywork.mapper;

import java.util.List;
import java.util.Map;

import com.boot.util.CommonEntity;
import com.boot.web.todaywork.model.DoufuTodayWork;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 赵祖龙
 * 当天工作记录信息表DAO层
 * 2019-09-07
 */
public interface DoufuTodayWorkMapper extends Mapper<DoufuTodayWork>{

	/**
	 * 列表查询,返回的是通用实体，不受实体属性限制，相当于map
	 */
	List<CommonEntity> queryPageInfo(Map<String, Object> params);
	/**
	 * 列表查询,返回的是实体
	 */
	List<DoufuTodayWork> entityList(Map<String, Object> params);
	/**
	 * 查询单条数据,返回的是实体
	 */
	DoufuTodayWork queryOne(Map<String, Object> params);
	/**
	 * 查询单条数据,返回的是通用实体，不受实体属性限制，相当于map
	 */
	CommonEntity queryOneCommon(Map<String, Object> params);
	/**
	 * 批量插入数据
	 */
	int insertBatch(List<DoufuTodayWork> list);
	/**
	 * 根据不同条件删除数据，条件可组合
	 */
	int deleteByParams(Map<String, Object> params);
	/**
	 * 批量更新
	 */
	int updateBatchEntity( List<DoufuTodayWork> list);
	
	/**
	 * 查询未提交的用户名称
	 */
	List<CommonEntity>  queryNotCommitUser(Map<String, Object> params);
	
	
	/**
	 * 查询指定时间段内指定的用户的提交日期列表
	 */
	List<CommonEntity>  countCommitTimes(Map<String, Object> params);
}