package com.jiuson.app.redisPubAndSubService;

import com.jiuson.app.RedisAPP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisAPP.class)
public class RedisPushMessageServiceTest {

    @Autowired
    private RedisPushMessageService redisPushMessageService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendMessageToSubChannel() {
        redisPushMessageService.sendMessageToSubChannel("channel", "message");
    }
}