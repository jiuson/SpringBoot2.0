package com.yipinketang.app.cache;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 创建MyBatis二级缓存类，关键是实现Cache（org.apache.ibatis.cache.Cache）
 */
public class MyBatisRedisCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisRedisCache.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final String id;//cache instance id
    private RedisTemplate redisTemplate;

    private static final long EXPIRE_TIME_IN_MINUTES = 30; //redis过期时间

    public MyBatisRedisCache(String id) {
        if(id == null){
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    /**
     * put query result to redis
     * @param key
     * @param value
     */
    public void putObject(Object key, Object value) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            logger.debug("Put query result to redis");
        }catch (Exception e){
            logger.error("Redis put failed", e);
        }

    }

    /**
     * Get cached query result from redis
     * @param key
     * @return
     */
    public Object getObject(Object key) {
        try{
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            return opsForValue.get(key);
        }catch (Exception e){
            logger.error("Redis get failed", e);
            return null;
        }
    }

    /**
     * Remove cached query result from redis
     * @param key
     * @return
     */
    public Object removeObject(Object key) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.delete(key);
            logger.debug("Remove cached query result from redis");
        }catch (Exception e){
            logger.error("Redis remove failed", e);
        }
        return null;
    }

    /**
     * Clears this cache instances
     */
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
           connection.flushDb();
           return null;
        });
        logger.debug("Clear all the cached query result from redis");
    }

    public int getSize() {
        return 0;
    }

    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    /*
     * 还可以通过注解@Bean直接在启动类注入全局的RedisTemplate
     * @return
     */
    private RedisTemplate getRedisTemplate(){
        if(null == redisTemplate){
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}
