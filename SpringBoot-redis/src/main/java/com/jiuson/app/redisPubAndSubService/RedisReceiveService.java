package com.jiuson.app.redisPubAndSubService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisReceiveService {

    @Autowired
    private RedisPushMessageService pushMessageService;

    public void receiveMessage(String message){
        System.out.println("收到订阅的消息:" + message);
    }
}
