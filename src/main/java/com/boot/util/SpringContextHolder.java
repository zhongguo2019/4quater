package com.boot.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;



/**
 * Spring的ApplicationContext的持有者,可以用静态方法的方式获取spring容器中的bean
 *
 * @author yj
 * @date 2018年5月27日 下午6:32:11
 */
@Component
public class SpringContextHolder implements ApplicationContextAware,DisposableBean  {
	

    private static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);


    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }


    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    private static void assertApplicationContext() {
        if (SpringContextHolder.applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性为null,请检查是否注入了SpringContextHolder!");
        }
    }


	  public static String getRootRealPath() {
	        String rootRealPath = "";
	        try {
	            rootRealPath = getApplicationContext().getResource("").getFile().getAbsolutePath();
	        } catch (IOException e) {
	            logger.warn("获取系统根目录失败");
	        }
	        return rootRealPath;
	    }

	    public static String getResourceRootRealPath() {
	        String rootRealPath = "";
	        try {
	            rootRealPath = new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
	        } catch (IOException e) {
	            logger.warn("获取资源根目录失败");
	        }
	        return rootRealPath;
	    }
	    /**
	     * 清除applicationContext静态变量.
	     */
	    public static void cleanApplicationContext() {
	        applicationContext = null;
	    }

	    private static void checkApplicationContext() {
	        if (applicationContext == null) {
	            throw new IllegalStateException(
	                    "applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
	        }
	    }

	    @Override
	    public void destroy() throws Exception {
	        SpringContextHolder.cleanApplicationContext();
	    }


}