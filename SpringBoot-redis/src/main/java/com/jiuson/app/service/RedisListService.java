package com.jiuson.app.service;

import com.jiuson.app.redisUtil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisListService {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/addDataToRedis")
    public Object addDataToRedis(@RequestParam(required = false, name = "key") String key,
                                 @RequestParam(required = false, name = "value") String value){
        redisUtil.set(key, value);
        return redisUtil.get(key);
    }

    @GetMapping("/addDataToRedisList")
    public Object addDataToRedisList(@RequestParam(required = false, name = "key") String key,
                                     @RequestParam(required = false, name = "value") String value){
        redisUtil.lSet(key, value);
        return redisUtil.lGetListSize(key);
    }

}
