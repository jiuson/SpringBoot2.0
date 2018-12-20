package com.jiuson.app.redisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisListUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisCommonUtil redisUtil;

    /**
     * 将value放入key对应的list右边
     * @param key
     * @param value
     * @return
     */
    public boolean rightPush(String key, Object value){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将value放入key对应的list右边，并设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean rightPush(String key, Object value, long time){
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if(time > 0){
                redisUtil.expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将values列表放入key对应的list右边
     * @param key
     * @param values
     * @return
     */
    public boolean rightPushAll(String key, List<Object> values){
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将values列表放入key对应的list右边，并设置过期时间
     * @param key
     * @param values
     * @param time
     * @return
     */
    public boolean rightPushAll(String key, List<Object> values, long time){
        try {
            redisTemplate.opsForList().rightPushAll(key, values);
            if(time > 0){
                redisUtil.expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从key对应的list右边删除一个数据并返回这个删除的数据
     * @param key
     * @return
     */
    public Object rightPop(String key){
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 从key对应的list右边删除一个数据并返回这个删除的数据，如果list里面没有数据，则保持连接直到有数据返回或超时
     * （阻塞删除）
     * @param key
     * @param time
     * @return
     */
    public Object rightPop(String key, long time){
        return redisTemplate.opsForList().rightPop(key, time, TimeUnit.SECONDS);
    }

    /**
     * 从key对应的list中删除count个value
     * @param key
     * @param count
     * @param value
     * @return
     */
    public long remove(String key, long count, Object value){
        try {
            long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据index修改key对应的list中的value
     * @param key
     * @param index
     * @param value
     * @return
     */
    public boolean updateIndex(String key, long index, Object value){
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取key对应的list缓存的内容
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> range(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key
     * @return
     */
    public long size(String key){
        try {
            return redisTemplate.opsForList().size(key);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引获取list中的值
     * @param key
     * @param index
     * @return
     */
    public Object index(String key, long index){
        try {
            return redisTemplate.opsForList().index(key, index);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }












}
