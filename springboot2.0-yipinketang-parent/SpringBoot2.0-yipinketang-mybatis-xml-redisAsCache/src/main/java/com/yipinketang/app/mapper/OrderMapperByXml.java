package com.yipinketang.app.mapper;

import com.yipinketang.app.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 该方式主要演示通过配置文件的方式使用MyBatis
 *
 * 如果启动类上面加了@MapperScan("com.yipinketang.app.mapper")注解，则这个地方不用再添加@Mapper注解,
 * 否则需要在每个Mapper接口上加上@Mapper注解，还是挺麻烦的
 *
 * 注意：方法名与对应映射文件中的sql标签的id对应
 */
@Mapper
public interface OrderMapperByXml {

    List<User> findAll();

    User findOne(Long id);

    int insert(User order);

    int update(User order);

    int delete(Long id);
}
