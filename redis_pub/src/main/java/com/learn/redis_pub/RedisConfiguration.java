package com.learn.redis_pub;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/5/26 16:18
 */
@Configuration
public class RedisConfiguration {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    public RedisMessageListenerContainer redisMessageListenerContainer() {

        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        return redisMessageListenerContainer;
    }

    public KeyExpireListener keyExpireListener() {
        return new KeyExpireListener(redisMessageListenerContainer());
    }
}
