package com.yipinketang.app;

import com.yipinketang.app.customEndpoint.CustomEndpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class AppJpa {

    public static void main(String[] args){
        SpringApplication.run(AppJpa.class, args);
    }

    /**
     * custom endpoint config
     */
//    @Configuration
//    static class CustomEndpointConfiguration{

//        @Bean
//        @ConditionalOnMissingBean
//        @ConditionalOnEnabledEndpoint
//        public CustomEndpoint getCustomEndpoint(){
//            return new CustomEndpoint();
//        }
//    }
}
