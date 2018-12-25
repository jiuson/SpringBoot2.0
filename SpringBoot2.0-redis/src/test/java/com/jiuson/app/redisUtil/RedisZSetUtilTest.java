package com.jiuson.app.redisUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RedisZSetUtilTest extends RedisUtilTest {

    @Autowired
    private RedisZSetUtil redisZSetUtil;
    private String key = "zSet";

    @Test
    public void zSetAdd() {
        Data data1 = new Data(1, "data1", "message1");
        boolean result = redisZSetUtil.zSetAdd(key, data1, 2);
        logger.info("zSetAdd result:" + result);
    }

    @Test
    public void zSetAdd1() {
    }

    @Test
    public void zSetRangeByScore() {
    }

    @Test
    public void zSetRemove() {
    }
}