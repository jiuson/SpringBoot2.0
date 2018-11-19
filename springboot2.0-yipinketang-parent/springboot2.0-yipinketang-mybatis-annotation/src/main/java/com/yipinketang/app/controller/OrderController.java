package com.yipinketang.app.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yipinketang.app.domain.User;
import com.yipinketang.app.jsonView.UserView;
import com.yipinketang.app.mapper.OrderMapperByAnnotation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapperByAnnotation orderMapperByAnnotation;


    /**
     * 查询所有order
     * @return
     */
    @JsonView(UserView.class)
    @GetMapping("/getAllOrder")
    public List<User> getAllOrder(){
        List<User> orderList = orderMapperByAnnotation.findAll();
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

        User order = orderMapperByAnnotation.findOne(id);
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
        return orderMapperByAnnotation.insert(input);
    }

    /**
     * 更新一条order
     * @param input
     */
    @PutMapping("/updateOrderById")
    public int  updateOrderById(@RequestBody User input){
        return orderMapperByAnnotation.update(input);
    }

    /**
     * 根据id删除user
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable("id") Long id){
        return orderMapperByAnnotation.delete(id);
    }
}
