package com.yipinketang.app.mapper;

import com.yipinketang.app.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * 该方式主要演示注解的方式使用MyBatis
 *
 * 如果启动类上面加了@MapperScan("com.yipinketang.app.mapper")注解，则这个地方不用再添加@Mapper注解,
 * 否则需要在每个Mapper接口上加上@Mapper注解，还是挺麻烦的
 *
 * @Select 是查询类的注解，所有的查询均使用这个
 * @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
 * @Insert 插入数据库使用，直接传入实体类会自动解析属性到对应的值
 * @Update 负责修改，也可以直接传入对象
 * @delete 负责删除
 *
 * 注意，使用#符号和$符号的不同：
 * // This example creates a prepared statement, something like select * from teacher where name = ?;
 * @Select("Select * from teacher where name = #{name}")
 * Teacher selectTeachForGivenName(@Param("name") String name);
 *
 * // This example creates n inlined statement, something like select * from teacher where name = 'someName';
 * @Select("Select * from teacher where name = '${name}'")
 * Teacher selectTeachForGivenName(@Param("name") String name);
 */
//@Mapper
public interface OrderMapperByXml {

    List<User> findAll();

    User findOne(Long id);

    int insert(User order);

    int update(User order);

    int delete(Long id);
}
