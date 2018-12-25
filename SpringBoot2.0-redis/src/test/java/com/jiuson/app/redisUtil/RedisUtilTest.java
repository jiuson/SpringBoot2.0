package com.jiuson.app.redisUtil;

import com.jiuson.app.RedisAPP;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisAPP.class)
public class RedisUtilTest {
    Logger logger = LoggerFactory.getLogger(RedisListUtilTest.class);
}
