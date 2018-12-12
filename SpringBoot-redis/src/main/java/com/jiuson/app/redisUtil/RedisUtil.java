package com.jiuson.app.redisUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis util
 */
@Component
public class RedisUtil {

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
    /**
     * 普通缓存获取
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存存放
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 带过期时间的缓存存放
     * @param key
     * @param value
     * @param time 小于0表示永不过期
     * @return
     */
    public boolean set(String key, String value, long time){
        try {
            if(time > 0){
                redisTemplate.opsForValue().set(key, value, time);
            }else {
                set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key, long delta){
        if (delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key, long delta){
        if (delta < 0){
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(key, delta);
    }

    /**------------------------------------Map------------------------------------------*/
    /**
     * HashGet
     * @param key not null
     * @param item not null
     * @return
     */
    public Object hGet(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key
     * @return
     */
    public Map<Object, Object> hmGet(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hash缓存存入Map
     * @param key
     * @param map
     * @return
     */
    public boolean hmSet(String key, Map<String, Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 带过期时间的hash缓存存入Map
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hmSet(String key, Map<String, Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 往一张hash表中放入数据，如果不存在将创建
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hSet(String key, String item, Object value){
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 往一张hash表中放入数据，如果不存在将创建,设置过期时间
     * @param key
     * @param item
     * @param value
     * @param time 如果已存在的hash表有过期时间，则会替换原有的时间
     * @return
     */
    public boolean hset(String key, String item, Object value, long time){
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key not null
     * @param item not null
     */
    public void hDel(String key, Object ... item){
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否存在该项的值
     * @param key  not null
     * @param item  not null
     * @return
     */
    public boolean hHashKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增，如果不存在，就会创建一个，并把新增后的值返回
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hIncr(String key, String item, double by){
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hDecr(String key, String item, double by){
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**------------------------------------Set------------------------------------------*/
}
