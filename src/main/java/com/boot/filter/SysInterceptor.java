package com.boot.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boot.web.sysuser.model.SysUser;
import com.krm.common.constant.Constant;

public class SysInterceptor extends HandlerInterceptorAdapter {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 进入拦截器后首先进入的方法 返回false则不再继续执行 返回true则继续执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {/*
								 * HttpSession session = request.getSession(true); SysUser sysuser = (SysUser)
								 * session.getAttribute(Constant.SESSION_LOGIN_USER); HttpServletRequest
								 * req=(HttpServletRequest)request; HttpServletResponse
								 * resp=(HttpServletResponse)response;
								 * 
								 * 如果是AJAX请求，则值为XMLHttpRequest String
								 * type=req.getHeader("X-Requested-With")==null ? "" :
								 * req.getHeader("X-Requested-With");
								 * 
								 * 
								 * 
								 * if (sysuser == null) { logger.
								 * info(" ----------------------------SysInterceptor sysuser 用户对象在 session中没有找到，统一返回到登录界面!-------------------"
								 * ); if("XMLHttpRequest".equals(type)) { //处理AJAX请求，设置响应头信息
								 * resp.setHeader("REDIRECT", "REDIRECT"); 需要跳转页面的URL
								 * resp.setHeader("CONTEXTPATH",
								 * req.getContextPath()+"/login/loginLayout.html"); }else {
								 * 
								 * resp.sendRedirect(req.getContextPath()+"/error");
								 * 
								 * }
								 * 
								 * logger.info(" ---------跳转到登录界面："+request.getContextPath() +
								 * "/loginvalidate/loginpage"+"-------------------");
								 * 
								 * return false;
								 * 
								 * } else { logger.
								 * info(" ----------------------------SysInterceptor 用户对象 sysuser 已经存在session中了，可以放行!-------------------"
								 * ); return true; }
								 */
     return true;
	}

	/**
	 * 生成视图时执行，可以用来处理异常，并记录在日志中
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception exception) {
		// -----------------//
	}

	/**
	 * - 生成视图之前执行，可以修改ModelAndView
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// ----------------------------//
	}

}