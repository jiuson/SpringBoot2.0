package com.yipinketang.app.customEndpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * custom endpoint via @Endpoint see AppJpa.java
 * @create by zuo 20181127
 */
@Component
@Endpoint(id = "customEndpoint")
public class CustomEndpoint {

    @ReadOperation
    public Map<String, String> getCustomEndpointInfo(){
        Map<String, String> result = new HashMap<>();
        result.put("info", "this is custom endpoint");
        return result;
    }
}
