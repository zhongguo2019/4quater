package com.krm.sso;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

//1. 引入slf4j接口的Logger和LoggerFactory
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import com.bonc.sso.client.IAuthHandle;

import com.boot.web.sys.service.SysUserService;
import com.boot.util.SpringContextHolder;
import com.boot.web.sys.model.SysUser;
import com.krm.common.constant.Constant;
// 根据filter中注册的单点登录配置，完成单点登录成功的回调方法。

public class AuthHandleImpl implements IAuthHandle {
	private final static Logger logger = LoggerFactory.getLogger(AuthHandleImpl.class);


	@Override
	public boolean onSuccess(HttpServletRequest request, HttpServletResponse response, String loginId) {
		logger.info("--------------4quater  单点登录回调，传入 loginId[" + loginId + "]---------------");
		if (null != request && !StringUtils.isEmpty(loginId)) {
			boolean b = setUserSession( loginId, request);
				logger.info("4quater 用户可以登录，用户名称为：" + loginId);

				return true;
			} else {
				logger.info("4quater 禁止用户登录，用户名称为：" + loginId);
			}
		return false;
	}
	
	
	public static boolean setUserSession(String username, HttpServletRequest request) {
		
		request.getSession().setAttribute(Constant.SESSION_LOGIN_USERNAME,username);
		return true;
	}

	/*
	 * public static boolean setUserSession(String username, HttpServletRequest
	 * request) {
	 * 
	 * // 从后台数据库中查询到sysuser ,然后置到session中 Map<String, Object> userInfoMap = new
	 * HashMap<>(); userInfoMap.put("username", username);
	 * 
	 * // logger.info("----4quater------后台根据用户名称得到用户的对象，用户名为：" +
	 * userInfoMap.get("username"); SysUser sysuser = (SysUser)
	 * request.getSession().getAttribute(Constant.SESSION_LOGIN_USER); if (null ==
	 * sysuser) { SysUser globalSysuser = new SysUser();
	 * globalSysuser.setUsername(username);
	 * request.getSession().setAttribute(Constant.SESSION_LOGIN_USER,
	 * globalSysuser); }
	 * 
	 * return true;
	 * 
	 * }
	 */

	/*
	 * public boolean userInit(HttpServletRequest request, String username) {
	 * boolean rtn = false; Map<String, Object> userInfo = new HashMap<String,
	 * Object>(); userInfo.put("username", username); SysUser sysuserQuery =
	 * sysUserService.verifyLogin(userInfo); if (sysuserQuery != null) { String
	 * projectGroupId = ""; projectGroupId =
	 * sysUserService.getProjectGroupId(userInfo);
	 * sysuserQuery.setProjectGroupId(projectGroupId); String remoteIP = "";
	 * sysuserQuery.setLoginIp(remoteIP);
	 * request.getSession().setAttribute(Constant.SESSION_LOGIN_USER, sysuserQuery);
	 * logger.info("通过微信登录到系统后台，将用户信息存储到全局的sessio中"); rtn = true;
	 * 
	 * } else { logger.info("用户姓名[" + username + "],没有在用户信息表中找到记录");
	 * 
	 * } return rtn;
	 * 
	 * }
	 */
}
