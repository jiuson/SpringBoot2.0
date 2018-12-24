package com.jiuson.app.redisUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.Assert.*;

public class RedisSetUtilTest extends RedisUtilTest {

    @Autowired
    private RedisSetUtil redisSetUtil;
    private String key = "set";


    @Test
    public void setAdd() {
        Data data1 = new Data(1, "data1", "message1");
        Data data2 = new Data(2, "data2", "message2");
//        Data data3 = new Data(3, "data3", "message3");
        String data4 = "data4";
        long result = redisSetUtil.setAdd(key, data1);
        long result2 = redisSetUtil.setAdd(key, data4);
        logger.info("setAdd result:" + result);
        logger.info("setAdd different object result:" + result2);//data1和data2都是Object类型，所以都可以存入集合
    }

    @Test
    public void setAdd1() {
        Data data3 = new Data(3, "data3", "message3");
        long result = redisSetUtil.setAddAndExpire(key, 30, data3);
        logger.info("setAdd result:" + result);
    }

    @Test
    public void setMembers() {
        Set<Object> result = redisSetUtil.setMembers(key);
        logger.info("setMembers result:" + result);
    }

    @Test
    public void setIsMember() {
        Data data2 = new Data(2, "data2", "message2");
        Data data3 = new Data(3, "data3", "message3");
        boolean result = redisSetUtil.setIsMember(key, data3);
        logger.info("setIsMember result:" + result);
    }

    @Test
    public void setSize() {
        long result = redisSetUtil.setSize(key);
        logger.info("setSize result:" + result);
    }

    @Test
    public void setRemove() {
        Data data2 = new Data(1, "data1", "message1");
        long result = redisSetUtil.setRemove(key, data2);//通过equals方法判断是否存在指定的对象，并删除
        logger.info("setRemove result:" + result);
    }
}