package com.jiuson.app;

import com.jiuson.app.redisUtil.RedisListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/redisList")
public class RedisListService {

    @Autowired
    private RedisListUtil redisListUtil;

    @GetMapping("/addDataToRedis")
    public Object addDataToRedis(@RequestParam(required = false, name = "key") String key,
                                 @RequestParam(required = false, name = "value") String value){
        return null;
    }

    @GetMapping("/addDataToRedisList")
    public Object addDataToRedisList(@RequestParam(required = false, name = "key") String key,
                                     @RequestParam(required = false, name = "value") String value){
        return null;
    }

}
