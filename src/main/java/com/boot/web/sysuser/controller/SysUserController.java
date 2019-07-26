package com.boot.web.sysuser.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.boot.web.sysuser.service.SysUserService;
import com.boot.util.JsonHelper;
import com.boot.web.sysuser.model.SysUser;
import com.krm.common.constant.Constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@RequestMapping("/sysuser")
public class SysUserController {
	@Autowired
	SysUserService sysUserService; 
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@RequestMapping("/loginCheck")
	String loginCheck(HttpServletRequest request) throws UnsupportedEncodingException {
		
		SysUser sysuser = (SysUser) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
		
		if(null == sysuser) {
			String data = request.getParameter("data") == null ? "" : request.getParameter("data");
			data = URLDecoder.decode(URLDecoder.decode(data, "utf-8"), "utf-8");
			Map<String, Object> userInfo = new HashMap<String, Object>();
	  
			if (!"".equals(data)) {
				userInfo = (Map<String, Object>) JsonHelper.decodeToMap(data);
				SysUser sysuserQuery = sysUserService.verifyLogin(userInfo);
				request.getSession().setAttribute(Constant.SESSION_LOGIN_USER, sysuserQuery);
				logger.info("loginCheck 得到了后台的用户信息，置到全局的session中，  进入到系统管理的首页面");
				
				
			}else {
				return "redirect:/sysuser/loginpage";
			}
			
		}
		return "redirect:/main/firstpage";
 
	}
	
	@RequestMapping("/loginpage")
	@ResponseBody
	ModelAndView  loginpage(HttpServletRequest request) {

	 return new ModelAndView("redirect:/login/loginLayout.html");

	}
 }