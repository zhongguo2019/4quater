package com.boot.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.boot.util.SpringContextHolder;
import com.boot.web.sys.model.SysUser;
import com.boot.web.sys.service.SysUserService;
import com.krm.common.constant.Constant;
import com.alibaba.druid.util.StringUtils;

@WebFilter(filterName = "addressFilter", urlPatterns = { "/*" })
@Order(1) // 指定过滤器的执行顺序,值越大越靠后执行
public class AddressFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static SysUserService sysUserService = null;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
		HttpSession session = httpRequest.getSession();
		HttpServletRequest hrequest = (HttpServletRequest) servletRequest;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
		SysUser sysuser = null;
		String userName = null;
		String isFromWeixin = "N";
		String uri = httpRequest.getRequestURI();// 取到你访问的资源

		logger.info("addressFilter 拦截到访问的地址及资源【" + uri + "】");

		if (null != session.getAttribute(Constant.SESSION_LOGIN_USERNAME)) {
			userName = session.getAttribute(Constant.SESSION_LOGIN_USERNAME).toString();
			userInit(hrequest, userName);
		}

		if (null != session.getAttribute(Constant.SESSION_LOGIN_USER)) {
			sysuser = (SysUser) session.getAttribute(Constant.SESSION_LOGIN_USER);
			logger.info("得到的用户名称为：" + sysuser.getName());
		}

		if (uri.indexOf("wx") < 0) {
			if (uri.equals("/")) { // 静态资源默认访问路径在/下，所以放过
				filterChain.doFilter(servletRequest, servletResponse);
			}

			if (isStaticResource(uri) || isAllowAddr(uri)) { // 判断是否是静态资源
			} else {
				if ("N".equals(isFromWeixin)) {
					if (null == sysuser && (httpRequest.getRequestURI().indexOf("login") < 0)) {
						logger.info("过滤器拦截，跳转到指定的首页：httpRequest.getRequestURI() " + httpRequest.getRequestURI());
						wrapper.sendRedirect(httpRequest.getContextPath() + "/main/loginlayout");

					}
				}
			}
		} else {
			session.setAttribute(Constant.SESSION_LOGIN_WXFLAG, "Y");
			isFromWeixin = "Y";
			logger.info("当前链接是来自于微信[" + isFromWeixin + "]");

		}

		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// 初始化filter时手动注入bean对象
		ServletContext context = filterConfig.getServletContext();
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(context);
		sysUserService = ac.getBean(SysUserService.class);

	}

	public boolean userInit(HttpServletRequest request, String username) {
		boolean rtn = false;
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("username", username);
		SysUser sysuserQuery = sysUserService.verifyLogin(userInfo);
		if (sysuserQuery != null) {
			String projectGroupId = "";
			projectGroupId = sysUserService.getProjectGroupId(userInfo);
			sysuserQuery.setProjectGroupId(projectGroupId);
			String remoteIP = "";
			sysuserQuery.setLoginIp(remoteIP);
			request.getSession().setAttribute(Constant.SESSION_LOGIN_USER, sysuserQuery);
			logger.info("通过微信登录到系统后台，将用户信息存储到全局的sessio中");
			rtn = true;
		} else {
			logger.info("用户姓名[" + username + "],没有在用户信息表中找到记录");
		}
		return rtn;
	}

	private Set<String> staticResourceTypes = new HashSet<String>();

	{
		staticResourceTypes.add(".html");
		staticResourceTypes.add(".css");
		staticResourceTypes.add(".js");
		staticResourceTypes.add(".png");
		staticResourceTypes.add(".jpg");
		staticResourceTypes.add(".otf");
		staticResourceTypes.add(".eot");
		staticResourceTypes.add(".svg");
		staticResourceTypes.add(".ttf");
		staticResourceTypes.add(".woff");
		staticResourceTypes.add(".gif");
		staticResourceTypes.add(".ico");
		staticResourceTypes.add(".txt");
		staticResourceTypes.add(".gzip");
		staticResourceTypes.add(".xz");
		staticResourceTypes.add(".tar.gz");
		staticResourceTypes.add(".tar.bz2");
		staticResourceTypes.add(".jar");
		staticResourceTypes.add(".war");
		staticResourceTypes.add(".7z");
		staticResourceTypes.add(".tgz");
		staticResourceTypes.add(".gz");
		staticResourceTypes.add(".map");

	}

	public final boolean isStaticResource(String url) {

		boolean result = false;
		if (org.apache.commons.lang3.StringUtils.isBlank(url)) {
			return result;
		}
		int start = url.lastIndexOf(".");
		if (start < 0) {
			return result;
		}
		String prex = url.substring(start, url.length());
		// logger.info("prex["+prex+"]");
		return staticResourceTypes.contains(prex);
	}

	private Set<String> staticAllowAddr = new HashSet<String>();
	{
		staticAllowAddr.add("wxconnect");
		// staticAllowAddr.add("firstpage");
	}

	public final boolean isAllowAddr(String url) {
		boolean result = false;
		if (org.apache.commons.lang3.StringUtils.isBlank(url)) {
			return result;
		}
		String address[] = url.split("/");
		if (null == address||address.length<0) {
			return result;
		}
		logger.info("过滤器判断前台地址是否为合法，得到的URL【"+url+"】");
		String prex="";
		if(address.length>0) {
		    prex = address[address.length - 1];
		}
		if(address.length==0) {
		    prex = address[address.length - 1];
		}
		
		logger.info("得到登录的地址的方法名称：prex[" + prex + "]");
		return staticAllowAddr.contains(prex);

	}

}