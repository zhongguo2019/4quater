package com.boot.web.todaywork.mapper;

import java.util.List;
import java.util.Map;

import com.krm.common.base.CommonEntity;
import com.boot.web.todaywork.model.DoufuTodayWork;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhaozulong
 * 当天工作记录信息表DAO层
 * 2019-07-29
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
}