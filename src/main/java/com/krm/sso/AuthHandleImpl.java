package com.krm.sso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

//1. 引入slf4j接口的Logger和LoggerFactory
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bonc.sso.client.IAuthHandle;


import com.boot.web.sys.service.SysUserService;
import com.boot.web.sys.model.SysUser;
import com.krm.common.constant.Constant;
// 根据filter中注册的单点登录配置，完成单点登录成功的回调方法。
public class AuthHandleImpl implements IAuthHandle {
	private final static Logger logger = LoggerFactory.getLogger(AuthHandleImpl.class);

	@Override
	public boolean onSuccess(HttpServletRequest request, HttpServletResponse response, String loginId) {
		logger.info("--------------4quater  单点登录回调，传入 loginId[" + loginId + "]---------------");
		if (null != request && !StringUtils.isEmpty(loginId)) {
			// 写入session 信息
			boolean b = setUserSession(loginId, request);
			if (b) {
				logger.info("4quater 用户可以登录，用户名称为：" + loginId);
				return true;
			} else {
				logger.info("4quater 禁止用户登录，用户名称为：" + loginId);
			}

		}
		return false;
	}

	public static boolean setUserSession(String username, HttpServletRequest request) {

		// 从后台数据库中查询到sysuser ,然后置到session中
		Map<String, Object> userInfoMap = new HashMap<>();
		userInfoMap.put("username", username);

		//logger.info("----4quater------后台根据用户名称得到用户的对象，用户名为：" + userInfoMap.get("username"));
		SysUser sysuser = (SysUser) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
		if (null == sysuser) {
			SysUser globalSysuser = new SysUser();
			globalSysuser.setUsername(username);
			request.getSession().setAttribute(Constant.SESSION_LOGIN_USER, globalSysuser);
		}

		return true;

	}

}
