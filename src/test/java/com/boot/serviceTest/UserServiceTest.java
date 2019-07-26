package com.boot.serviceTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.baseTest.SpringTestCase;


import com.boot.web.sysuser.service.SysUserService;
import com.boot.web.sysuser.model.SysUser;

public class UserServiceTest extends SpringTestCase{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired  
    private SysUserService sysUserService; 
	@Test  
    public void selectUserByIdTest(){  
        SysUser user = sysUserService.selectUserByName("admin");  
        logger.info("查找结果" + user);  
    }  

}
