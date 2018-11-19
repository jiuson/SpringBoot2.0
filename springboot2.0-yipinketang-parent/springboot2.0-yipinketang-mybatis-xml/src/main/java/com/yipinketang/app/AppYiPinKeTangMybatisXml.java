package com.yipinketang.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yipinketang.app.mapper")
public class AppYiPinKeTangMybatisXml {

    public static void main(String[] args){
        SpringApplication.run(AppYiPinKeTangMybatisXml.class, args);
    }
}
