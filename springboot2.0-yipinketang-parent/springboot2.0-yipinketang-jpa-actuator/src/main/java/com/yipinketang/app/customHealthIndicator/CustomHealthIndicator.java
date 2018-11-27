package com.yipinketang.app.customHealthIndicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * implement custom health indicator via implements HealthIndicator interface
 * @create by zuo 20181127
 */
@Component("customIndicator")
public class CustomHealthIndicator implements HealthIndicator {

    private static final String info = "this is custom health indicator";
    @Override
    public Health health() {
        int code = check();
        if(code != 0){
            Health.down().withDetail("code", code).withDetail("info", info).build();
        }
        return Health.up().withDetail("code", code).withDetail("info", info).build();
    }

    private int check(){
        return 0;
    }
}
