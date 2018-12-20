package com.jiuson.app.redisUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class RedisMapUtilTest extends RedisUtilTest{

    @Autowired
    private RedisMapUtil redisMapUtil;

    @Test
    public void hashPut() {
        boolean result = redisMapUtil.hashPut("map", "map_key_1", new Data(1, "data", "message"));
        logger.info("hashPut result:" + result);
    }

    @Test
    public void hashPut1() {
    }

    @Test
    public void hashPutAll() {
        Map<String, Object> map = new HashMap<>();
        map.put("map_key_all_1", new Data(1, "all_data", "all_message"));
        map.put("map_key_all_2", new Data(2, "all_data", "all_message"));
        boolean result = redisMapUtil.hashPutAll("map", map);
        logger.info("hashPutAll result:" + result);
    }

    @Test
    public void hashPutAll1() {
    }

    @Test
    public void hashGet() {
        Data result = (Data) redisMapUtil.hashGet("map", "map_key_1");
        logger.info("hashGet result:" + result);
    }

    @Test
    public void hashEntries() {
        Map<Object, Object> result = redisMapUtil.hashEntries("map");
        logger.info("hashEntries result:" + result);
    }

    @Test
    public void hashKeys() {
        Set<Object> result = redisMapUtil.hashKeys("map");
        logger.info("hashKeys result:" + result);
    }

    @Test
    public void hashHasKey() {
    }

    @Test
    public void hashSize() {
        long result = redisMapUtil.hashSize("map");
        logger.info("hashSize result:" + result);
    }

    @Test
    public void hashValues() {
        List<Object> result = redisMapUtil.hashValues("map");
        logger.info("hashValues result:" + result);
    }

    @Test
    public void hashDelete() {
        redisMapUtil.hashDelete("map", "map_key_1");
    }
}