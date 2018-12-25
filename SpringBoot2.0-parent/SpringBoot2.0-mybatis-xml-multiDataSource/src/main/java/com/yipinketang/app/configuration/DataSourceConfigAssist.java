package com.yipinketang.app.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * create 20181120
 * 配置第二据源
 * 跟主数据源的区别是注解@Primary
 */
@Configuration
//给mapper注入指定的SqlSessionTemplate
@MapperScan(basePackages = "com.yipinketang.app.mapper.assist" , sqlSessionTemplateRef = "assistSqlSessionTemplate")
public class DataSourceConfigAssist {

    @Bean(name = "assistDataSource")//指定Bean的名称
    @ConfigurationProperties(prefix = "spring.datasource.assist")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "assistSqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("assistDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper" +
                "/assist/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "assistTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("assistDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "assistSqlSessionTemplate")
    public SqlSessionTemplate primarySqlSessionTemplate(@Qualifier("assistSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
