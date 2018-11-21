package com.yipinketang.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * create 20181120
 * SpringBoot启动类，主要做一些框架的配置
 */

@SpringBootApplication
//注意：因为已经有DataSource的Configuration配置文件，已经添加了@MapperScan注解，所以这个地方再添加是会报错的：No MyBatis mapper was found in '[com
// .yipinketang.app.mapper]' package. Please check your configuration.
//@MapperScan("com.yipinketang.app.mapper")
public class AppYiPinKeTangMybatisAnnotationMultiDataSource {

    public static void main(String[] args){
        SpringApplication.run(AppYiPinKeTangMybatisAnnotationMultiDataSource.class, args);
    }
}
