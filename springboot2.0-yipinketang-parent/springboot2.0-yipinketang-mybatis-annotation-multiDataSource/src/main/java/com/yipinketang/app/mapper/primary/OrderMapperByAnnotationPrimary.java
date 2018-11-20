package com.yipinketang.app.mapper.primary;

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
public interface OrderMapperByAnnotationPrimary {
    @Select("select * from user")
    @Results({
            @Result(property = "orderType", column = "order_type", javaType = User.OrderType.class),
            @Result(property = "createDate", column = "create_date", javaType = Date.class),
            @Result(property = "modifyDate", column = "modify_date", javaType = Date.class),
            @Result(property = "orderName", column = "order_name", javaType = String.class)
    })
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "orderType", column = "order_type", javaType = User.OrderType.class),
            @Result(property = "createDate", column = "create_date", javaType = Date.class),
            @Result(property = "modifyDate", column = "modify_date", javaType = Date.class),
            @Result(property = "orderName", column = "order_name", javaType = String.class)
    })
    User findOne(Long id);

    @Insert("insert into user(number, order_type, order_name, creator, create_date, modify_date) values(#{number}, " +
            "#{orderType}, #{orderName}, #{creator}, #{createDate}, #{modifyDate})")
    int insert(User order);

    @Update("update user set order_name = #{orderName} where id = #{id}")
    int update(User order);

    @Delete("delete from user where id = #{id}")
    int delete(Long id);
}
