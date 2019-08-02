package com.boot.web.sysuser.mapper;

 

import org.apache.ibatis.annotations.Param;

import com.boot.web.sysuser.model.SysUser;

import tk.mybatis.mapper.common.Mapper;


public interface SysUserMapper extends Mapper<SysUser>{  
    public SysUser selectUserByName(@Param("userName") String userName);  
  
}  
