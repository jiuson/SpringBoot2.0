package com.jiuson.app.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisZSetUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisCommonUtil redisUtil;

    /**
     * 将value对象添加到key对应带排序功能的set集合中，同时给这个value设置一个score，作为排序依据
     * (Add value to a sorted set at key, or update its score if it already exists.)
     * @param key
     * @param value
     * @param score
     * @return
     */
    public boolean zSetAdd(String key, Object value, double score){
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将value对象添加到key对应带排序功能的set集合中，同时给这个value设置一个score，作为排序依据，并设置key的过期时间
     * (Add value to a sorted set at key, or update its score if it already exists.)
     * @param key
     * @param value
     * @param score
     * @param time
     * @return
     */
    public boolean zSetAdd(String key, Object value, double score, long time){
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            if (time > 0){
                redisUtil.expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据score的最大值和最小值取出key对应的带排序的set集合中的对象
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<Object> zSetRangeByScore(String key, double min, double max){
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除key对应带排序set集合中的指定values对象
     * @param key
     * @param values
     * @return
     */
    public long zSetRemove(String key, Object ... values){
        try {
            return redisTemplate.opsForZSet().remove(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
