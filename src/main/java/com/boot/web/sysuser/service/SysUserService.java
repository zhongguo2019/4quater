package com.boot.web.sysuser.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.boot.web.sysuser.mapper.SysUserMapper;
import com.boot.web.sysuser.model.SysUser;
@Service
public class SysUserService {
	@Autowired  
	private SysUserMapper  sysUserMapper;  
	

	public SysUser selectUserByName(String userName) {
		return sysUserMapper.selectUserByName(userName);  
	}


	public SysUser verifyLogin(Map<String, Object> userInfo) {
		return selectUserByName(userInfo.get("username").toString());
	}

}
