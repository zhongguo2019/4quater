package com.boot.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.boot.web.sysuser.model.SysUser;
import com.krm.common.constant.Constant;

@WebFilter(filterName = "addressFilter", urlPatterns = { "/main/*","/informanage/*"})
public class AddressFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpSession session = httpRequest.getSession();
		HttpServletRequest hrequest = (HttpServletRequest) servletRequest;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
		
		SysUser sysuser = (SysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
		if (sysuser==null) {
			logger.info(" ----------------------------Filter sysuser 用户对象在 session中没有找到，统一返回到登录界面!-------------------");
		       
			if (hrequest.getRequestURI().indexOf("/index") != -1 
					|| hrequest.getRequestURI().indexOf("/asd") != -1
					|| hrequest.getRequestURI().indexOf("/online") != -1
					|| hrequest.getRequestURI().indexOf("/login") != -1) {
				logger.info(" ----------------------------Filter 地址是允许的页面的关键字，放行!-------------------");
				   
				filterChain.doFilter(servletRequest, servletResponse);
				
			} else {
				logger.info(" ----------------------------Filter 登录中没有相关的关键字，返回到登录道页!-------------------");
				
				wrapper.sendRedirect("/login/loginLayout.html");
			}
		}else {
			logger.info(" ----------------------------用户对象已经在session中，可以进行继续访问!-------------------");
			   
			filterChain.doFilter(servletRequest, servletResponse);
		}

		
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}