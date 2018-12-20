package com.jiuson.app.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class RedisMapUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisCommonUtil redisUtil;

    /**
     * 往key对应的Map中存放数据
     * @param key rediskey
     * @param hashKey Mapkey
     * @param value Mapvalue
     * @return
     */
    public boolean hashPut(String key, String hashKey, Object value){
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 往key对应的Map中存放数据，并设置key的过期时间
     * @param key
     * @param hashKey
     * @param value
     * @param time 如果已存在的hash表有过期时间，则会替换原有的时间
     * @return
     */
    public boolean hashPut(String key, String hashKey, Object value, long time){
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
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
     * 往key对应的Map中存放Map数据
     * @param key
     * @param map
     * @return
     */
    public boolean hashPutAll(String key, Map<String, Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 往key对应的Map中存放Map数据，并设置key的过期时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hashPutAll(String key, Map<String, Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
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
     * 获取指定key对应Map的hashKey
     * @param key rediskey
     * @param hashKey Mapkey
     * @return
     */
    public Object hashGet(String key, String hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取指定key的map
     * @param key
     * @return
     */
    public Map<Object, Object> hashEntries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取key对应的Map的所有hashKey
     * @param key
     * @return
     */
    public Set<Object> hashKeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 判断key对应的Map中是否包含hashKey
     * @param key
     * @param hashKey
     * @return
     */
    public boolean hashHasKey(String key, Object hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 返回key对应的Map的size
     * @param key
     * @return
     */
    public long hashSize(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 获取key对应的Map的value
     * @param key
     * @return
     */
    public List<Object> hashValues(String key){
        return redisTemplate.opsForHash().values(key);
    }

    /**
     * 删除key对应的Map中的指定数据
     * @param key
     * @param hashKeys
     */
    public void hashDelete(String key, Object ... hashKeys){
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

}
