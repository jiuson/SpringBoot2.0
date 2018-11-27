package com.yipinketang.app.customHealthIndicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * implement custom health indicator via extends AbstractHealthIndicator abstract class
 * @create by zuo 20181127
 */
@Component("customIndicator2")
public class CustomerHealthIndicator2 extends AbstractHealthIndicator {

    private static final String info = "this is custom health indicator";

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int code = check();
        if(code != 0){
            builder.down().withDetail("code", code).withDetail("info", info).build();
        }
        builder.up().withDetail("code", code).withDetail("info", info).build();
    }

    private int check(){
        return 0;
    }
}
