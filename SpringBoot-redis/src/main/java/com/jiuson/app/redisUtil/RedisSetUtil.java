package com.jiuson.app.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RedisSetUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisCommonUtil redisUtil;

    /**
     * 将数据放入set缓存
     * @param key
     * @param values
     * @return 成功个数
     */
    public long setAdd(String key, Object ... values){
        try {
            return redisTemplate.opsForSet().add(key, values);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set放入缓存，并设置过期时间
     * @param key
     * @param time
     * @param values
     * @return
     */
    public long setAdd(String key, long time, Object ... values){
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0){
                redisUtil.expire(key, time);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取key对应的set的值
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断key对应的set中是否存在value
     * @param key
     * @param value
     * @return
     */
    public boolean sHashkey(String key, Object value){
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 获取key对应的set的size
     * @param key
     * @return
     */
    public long setSize(String key){
        try {
            return redisTemplate.opsForSet().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 从key对应的set中删除values
     * @param key
     * @param values
     * @return
     */
    public long setRemove(String key, Object ... values){
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
