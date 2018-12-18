package com.jiuson.app.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis util
 */
@Component
public class RedisCommonUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**------------------------------------common------------------------------------------*/
    /**
     * 指定缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time){
        try {
            if (time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     * @param key   not null
     * @return  返回0代表永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean del(String ... key){
        if(key != null && key.length > 0){
            if (key.length == 1){
                redisTemplate.delete(key[0]);
                return true;
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
                return true;
            }
        }
        return false;
    }

    /**------------------------------------String------------------------------------------*/

    /**------------------------------------Map------------------------------------------*/


    /**------------------------------------Set------------------------------------------*/



    /**------------------------------------List------------------------------------------*/



}
