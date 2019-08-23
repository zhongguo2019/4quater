

package com.boot.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.boot.util.CommonEntity;
import com.boot.web.sys.model.SysOrgan;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 */

public interface SysOrganMapper extends Mapper<SysOrgan> {

    public List<CommonEntity> findPageInfo(Map<String, Object> params);

    public int deleteOrganByRootId(String code);

    public int updateParentIds(SysOrgan sysOrgan);

    public SysOrgan findOrganCompanyIdByDepId(String depId);

    //得到用户数据范围
    public List<String> findUserDataScopeByUserId(String userId);

    public List<String> findOrganIdsByRootId(String rootId);

    public SysOrgan findOrganByCode(Map<String, Object> params);

}
