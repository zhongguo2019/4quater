

package com.boot.web.sys.mapper;

import java.util.List;
import java.util.Map;

import com.boot.web.sys.model.SysDict;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 */

public interface SysDictMapper extends Mapper<SysDict> {
    public List<SysDict> list(Map<String, Object> params);
}
