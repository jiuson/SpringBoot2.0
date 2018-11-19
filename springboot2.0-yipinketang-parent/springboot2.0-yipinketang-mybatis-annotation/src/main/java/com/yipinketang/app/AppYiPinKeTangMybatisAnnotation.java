package com.yipinketang.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot启动类，主要做一些框架的配置
 */

@SpringBootApplication
@MapperScan("com.yipinketang.app.mapper")
public class AppYiPinKeTangMybatisAnnotation {

    public static void main(String[] args){
        SpringApplication.run(AppYiPinKeTangMybatisAnnotation.class, args);
    }
}
