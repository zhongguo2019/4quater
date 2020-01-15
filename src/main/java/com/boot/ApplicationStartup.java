package com.boot;


import com.boot.util.qq.weixin.mp.aes.WeiXinUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author Stone Yuan
 * @create 2017-12-02 21:54
 * @description
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		/*
		 * IPermissionService service =
		 * contextRefreshedEvent.getApplicationContext().getBean(IPermissionService.
		 * class); service.loadUserPermissionIntoRedis();
		 */
		
		WeiXinUtil weiXinUtil = new WeiXinUtil();
		weiXinUtil.setRedisToken();
    }
}