package com.boot.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.boot.filter.SysInterceptor;

@Configuration          //使用注解 实现拦截
public class SessionConfiguration extends WebMvcConfigurerAdapter    {

	public void addInterceptors(InterceptorRegistry registry) {
		//指定的拦截路径的范围  主要看一下controller中写的路径访问方式
        InterceptorRegistration registration = registry.addInterceptor(new SysInterceptor());     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");                    //所有路径都被拦截SysInterceptor
        registration.excludePathPatterns("/loginvalidate/*","/login","/error","/static/**","/logout");  
	}
	
}
