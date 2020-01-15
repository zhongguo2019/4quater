package com.boot.configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import com.boot.util.qq.weixin.mp.aes.WeiXinUtil;


/**
 * 监听所有db的过期事件__keyevent@*__:expired"
 * @author lsm
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
	Logger logger = LoggerFactory.getLogger(this.getClass());
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();

        if(expiredKey.startsWith("token")){
            //如果是Order:开头的key，进行处理
            logger.info("redis token 已经过期,重新从服务器中获取");
            WeiXinUtil weiXinUtil = new WeiXinUtil();
            weiXinUtil.setRedisToken();
            
        }
    }
}