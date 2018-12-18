package com.jiuson.app;

import com.jiuson.app.redisUtil.RedisStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redisString")
public class RedisStringService {

    @Autowired
    private RedisStringUtil redisStringUtil;

    @PostMapping
    public Object addStringValue(@RequestParam(name = "key") String key,
                            @RequestParam(name = "value") String value) {
        return redisStringUtil.set(key, value);
    }

    public Object addStringValueAndExpire(@RequestParam(name = "key") String key,
                                          @RequestParam(name = "value") String value,
                                          @RequestParam(name = "time") Long time){
        return redisStringUtil.set(key, value, time);
    }

    @GetMapping
    public Object getStringValue(@RequestParam(name = "key") String key){
        return redisStringUtil.get(key);
    }
}
