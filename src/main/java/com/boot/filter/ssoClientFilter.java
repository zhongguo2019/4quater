package com.boot.filter;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.core.annotation.Order;

import com.bonc.sso.client.SSOFilter;
import org.apache.log4j.Logger;
/*
@WebFilter(filterName = "ssoFilter", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "serverName", value = "192.168.2.11:9080"),
		@WebInitParam(name = "casServerUrlPrefix", value = "http://192.168.2.198:8081/sso"),
		@WebInitParam(name = "casServerLoginUrl", value = "http://192.168.2.198:8081/sso/login"),
		@WebInitParam(name = "singleSignOut", value = "true"),
		@WebInitParam(name = "skipUrls", value = "userSync.action"),
		@WebInitParam(name = "loginUserHandle", value = "com.krm.sso.AuthHandleImpl"),
		@WebInitParam(name = "encoding", value = "UTF-8") })
@Order(1) // 指定过滤器的执行顺序,值越大越靠后执行
*/
// 下面的代码为将系统交给单点登录时使用。
//public class ssoClientFilter extends SSOFilter  {
public class ssoClientFilter {
	private final static Logger logger= Logger.getLogger(ssoClientFilter.class);
}
