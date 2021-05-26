package com.learn.redis_pub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.nio.charset.StandardCharsets;

/**
 * 需要修改redis配置文件
 * sub:
 * notify-keyspace-events EX
 * redis key过期监听事件
 *
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/5/26 16:01
 */
@Slf4j
public class KeyExpireListener extends KeyExpirationEventMessageListener {

    public PatternTopic patternTopic() {
        return new PatternTopic("__keyevent@0__:expired");
    }

    public KeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        String key = new String(message.getBody(), StandardCharsets.UTF_8);
        String patternStr = new String(pattern, StandardCharsets.UTF_8);
        log.info("监听redis中key失效事件[channel:{},key:{},patternStr:{}]", channel, key, patternStr);
    }
}
