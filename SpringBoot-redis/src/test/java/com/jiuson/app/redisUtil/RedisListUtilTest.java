package com.jiuson.app.redisUtil;

import com.jiuson.app.RedisAPP;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisAPP.class)
public class RedisListUtilTest {
    private Logger logger = LoggerFactory.getLogger(RedisListUtilTest.class);

    @Autowired
    private RedisListUtil redisListUtil;

    /**----------------------------value是String类型--------------------------------*/
    @Test
    public void rightPushString() {
        boolean result = redisListUtil.rightPush("listString", "value2");
        logger.info("rightPushString result:" + result);
    }

    @Test
    public void rightPopString(){
        String result = (String) redisListUtil.rightPop("listString");
        logger.info("rightPopString result:" + result);
    }

    public void rightPopStringExpire(){
        String result = (String) redisListUtil.rightPop("listString", 30);
        logger.info("" + result);
    }

    @Test
    public void rightPushStringExpire(){
        boolean result = redisListUtil.rightPush("listString", "value3", 5);
        logger.info("rightPushStringExpire result:" + result);
    }

    @Test
    public void rightPushStringList(){
        List<Object> listString = new ArrayList<>();
        listString.add("listString_value1");
        listString.add("listString_value2");
        boolean result = redisListUtil.rightPushAll("listString", listString);
        logger.info("rightPushStringList result:" + result);
    }

    @Test
    public void rightPushStringListExpire(){
        List<Object> listStringExpire = new ArrayList<>();
        listStringExpire.add("listStringExpire_value1");
        listStringExpire.add("listStringExpire_value2");
        boolean result = redisListUtil.rightPushAll("listString", listStringExpire, 30);//过期时间针对的是listString这个可以对应的整个列表，而不是往这个列表添加的两个值
        logger.info("rightPushStringListExpire result:" + result);
    }

    @Test
    public void rangeString(){
        List<Object> result = redisListUtil.range("listString", 0, 10);
        logger.info("rangeString result:" + result);
    }

    /**----------------------------value是Object类型--------------------------------*/
    @Test
    public void rightPushObject() {
        Data data = new Data(1, "listData", "messageData");
        boolean result = redisListUtil.rightPush("listObject", data);
        logger.info("rightPushObject result:" + result);
    }

    @Test
    public void rightPushObjectExpire() {
        Data data = new Data(1, "listData", "messageData");
        boolean result = redisListUtil.rightPush("listObject", data);
        logger.info("rightPushObjectExpire result:" + result);
    }

    @Test
    public void rightPushListObject(){
        List<Object> listObject = new ArrayList<>();
        listObject.add(new Data(1, "listObject_data1", "listObject_message1"));
        listObject.add(new Data(2, "listObject_data2", "listObject_message2"));
        boolean result = redisListUtil.rightPushAll("listObject", listObject);
        logger.info("rightPushListObject result:" + result);
     }

    @Test
    public void rightPushListObjectExpire(){
        List<Object> listObjectExpire = new ArrayList<>();
        listObjectExpire.add(new Data(1, "listObjectExpire_data1", "listObjectExpire_message1"));
        listObjectExpire.add(new Data(2, "listObjectExpire_data2", "listObjectExpire_message2"));
        boolean result = redisListUtil.rightPushAll("listObject", listObjectExpire, 30);
        logger.info("rightPushListObjectExpire result:" + result);
    }

    @Test
    public void rangeObject(){
        List<Object> result = redisListUtil.range("listObject", 0, 10);
        logger.info("rangeObject result:" + result);
    }

    @Test
    public void sizeObject(){
        long size = redisListUtil.size("listObject");
        logger.info("sizeObject size:" + size);
    }

}