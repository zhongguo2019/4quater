package com.boot.web.wxgroup.mapper;

import java.util.List;
import java.util.Map;

import com.boot.util.CommonEntity;
import com.boot.web.wxgroup.model.WxUserGroup;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author 赵祖龙
 * 微信用户分组信息表DAO层
 * 2019-09-26
 */
public interface WxUserGroupMapper extends Mapper<WxUserGroup>{

	/**
	 * 列表查询,返回的是通用实体，不受实体属性限制，相当于map
	 */
	List<CommonEntity> queryPageInfo(Map<String, Object> params);
	/**
	 * 列表查询,返回的是实体
	 */
	List<WxUserGroup> entityList(Map<String, Object> params);
	/**
	 * 查询单条数据,返回的是实体
	 */
	WxUserGroup queryOne(Map<String, Object> params);
	/**
	 * 查询单条数据,返回的是通用实体，不受实体属性限制，相当于map
	 */
	CommonEntity queryOneCommon(Map<String, Object> params);
	/**
	 * 批量插入数据
	 */
	int insertBatch(List<WxUserGroup> list);
	/**
	 * 根据不同条件删除数据，条件可组合
	 */
	int deleteByParams(Map<String, Object> params);
	/**
	 * 批量更新
	 */
	int updateBatchEntity( List<WxUserGroup> list);
}