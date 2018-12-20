package com.jiuson.app.redisPubAndSubService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * redis往指定channel推送消息
 */
@Service
public class RedisPushMessageService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向channel通道发送消息
     * @param channel
     * @param message
     */
    public void sendMessageToSubChannel(String channel, String message){
        stringRedisTemplate.convertAndSend(channel, message);
    }
}
