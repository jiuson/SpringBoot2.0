package springboot.jiuson.messagingstompwebsocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用@MessageMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 配置消息中间件
     * @param config
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "queue");//topic用于广播，queue用于潘p2p，其实广播和p2p可以在一个destinationPrefixes实现
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * 配置websocket端点，可以配置多个端点，以下配置两个端点
     * /gs-guide-websocket 用于广播消息
     * /queue 用于点对点
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket")
                .setAllowedOrigins("*")
                .withSockJS();
        registry.addEndpoint("/user")
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
