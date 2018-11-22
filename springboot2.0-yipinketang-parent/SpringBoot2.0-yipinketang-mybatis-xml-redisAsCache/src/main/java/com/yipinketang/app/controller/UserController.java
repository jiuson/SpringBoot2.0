package com.yipinketang.app.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yipinketang.app.domain.User;
import com.yipinketang.app.jsonView.UserView;
import com.yipinketang.app.mapper.OrderMapperByXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class UserController {

    @Autowired
    private OrderMapperByXml orderMapperByXml;
    @Autowired
    DataSource dataSource;//验证使用的哪个数据源

    /**
     * 查询所有order
     * 多次请求改接口，查看时候启用二级缓存
     * @return
     */
    @JsonView(UserView.class)
    @GetMapping("/getAllOrder")
    public List<User> getAllOrder(){
        System.out.println(dataSource);//测试使用的数据源
        List<User> orderList = orderMapperByXml.findAll();
        if (null == orderList || orderList.size() <= 0){

        }
        return orderList;
    }

    /**
     * 根据id获取order
     * @param id
     * @return
     */
    @GetMapping("/getOrderById/{id}")
    public User getOrderById(@PathVariable("id") Long id){

        User order = orderMapperByXml.findOne(id);
        if(null == order){

        }
        return order;
    }

    /**
     * 新增一条order
     * @param input
     */
    @PostMapping("/insertOrder")
    public int insertOrder(@RequestBody User input){
        return orderMapperByXml.insert(input);
    }

    /**
     * 更新一条order
     * @param input
     */
    @PutMapping("/updateOrderById")
    public int  updateOrderById(@RequestBody User input){
        return orderMapperByXml.update(input);
    }

    /**
     * 根据id删除user
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable("id") Long id){
        return orderMapperByXml.delete(id);
    }
}
