package com.boot.web.tomorrowplan.mapper;

import java.util.List;
import java.util.Map;

import com.boot.util.CommonEntity;
import com.boot.web.tomorrowplan.model.DoufuTomorrowPlan;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 赵祖龙
 * 明天工作计划表DAO层
 * 2019-08-26
 */
public interface DoufuTomorrowPlanMapper extends Mapper<DoufuTomorrowPlan>{

	/**
	 * 列表查询,返回的是通用实体，不受实体属性限制，相当于map
	 */
	List<CommonEntity> queryPageInfo(Map<String, Object> params);
	/**
	 * 列表查询,返回的是实体
	 */
	List<DoufuTomorrowPlan> entityList(Map<String, Object> params);
	/**
	 * 查询单条数据,返回的是实体
	 */
	DoufuTomorrowPlan queryOne(Map<String, Object> params);
	/**
	 * 查询单条数据,返回的是通用实体，不受实体属性限制，相当于map
	 */
	CommonEntity queryOneCommon(Map<String, Object> params);
	/**
	 * 批量插入数据
	 */
	int insertBatch(List<DoufuTomorrowPlan> list);
	/**
	 * 根据不同条件删除数据，条件可组合
	 */
	int deleteByParams(Map<String, Object> params);
	/**
	 * 批量更新
	 */
	int updateBatchEntity( List<DoufuTomorrowPlan> list);
}