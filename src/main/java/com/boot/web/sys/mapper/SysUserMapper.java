

package com.boot.web.sys.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.boot.util.CommonEntity;
import com.boot.web.sys.model.SysUser;

import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 */

public interface SysUserMapper extends Mapper<SysUser> {

    public List<CommonEntity> findPageInfo(Map<String, Object> params);

    public int saveUser(SysUser sysUser);
    public SysUser selectUserByName(@Param("username") String username);  
    public List<CommonEntity>   getUserGroup(  @Param("username")  String username  );
    public List<CommonEntity>   getProdOfProj( @Param("projectname")  String projectname);
    public List<CommonEntity>   getModuOfProd(  @Param("productid") String productid);
    public SysUser queryOneUser(Map<String, Object> params);
    
}
