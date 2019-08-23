

package com.boot.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.boot.web.sys.model.SysLog;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 */

public interface SysLogMapper extends Mapper<SysLog> {


    public List<SysLog> findSysLogList(Map<String, Object> params);

    public List<SysLog> findPageInfo(Map<String, Object> params);

    public SysLog getById(String id);


}
