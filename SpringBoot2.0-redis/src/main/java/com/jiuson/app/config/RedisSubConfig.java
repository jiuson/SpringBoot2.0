package com.jiuson.app.config;

import com.jiuson.app.redisPubAndSubService.RedisReceiveService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * redis发布订阅的配置
 */
@Configuration
public class RedisSubConfig {

    /**
     * 初始化发布/订阅容器
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("channel"));//channel是订阅通道
        return container;
    }

    /**
     * 收到消息后的处理方法
     * @param redisReceiveService
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisReceiveService redisReceiveService){
        return new MessageListenerAdapter(redisReceiveService, "receiveMessage");//通过反射机制调用RedisReceiveService的receiveMessage方法
    }

    /**
     * 通过默认的RedisConnectionFactory创建一个StringRedisTemplate bean
     * @param connectionFactory
     * @return
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }

}
