package com.yipinketang.app.controller;

import com.yipinketang.app.domain.User;
import com.yipinketang.app.mapper.assist.OrderMapperByXmlAssist;
import com.yipinketang.app.mapper.primary.OrderMapperByXmlPrimary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create 20181120
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapperByXmlPrimary orderMapperByXmlPrimary;
    @Autowired
    private OrderMapperByXmlAssist orderMapperByXmlAssist;


    /**
     * 从主数据源查询所有order
     * @return
     */
    @GetMapping("/getAllOrderFromPrimaryDataSource")
    public List<User> getAllOrderFromPrimaryDataSource(){
        List<User> orderList = orderMapperByXmlPrimary.findAll();
        if (null == orderList || orderList.size() <= 0){

        }
        return orderList;
    }

    /**
     * 从第二数据源查询所有order
     * @return
     */
    @GetMapping("/getAllOrderFromAssistDataSource")
    public List<User> getAllOrderFromAssistDataSource(){
        List<User> orderList = orderMapperByXmlAssist.findAll();
        if (null == orderList || orderList.size() <= 0){

        }
        return orderList;
    }
    /**
     * 从主数据源根据id获取order
     * @param id
     * @return
     */
    @GetMapping("/getOrderById/{id}")
    public User getOrderById(@PathVariable("id") Long id){

        User order = orderMapperByXmlPrimary.findOne(id);
        if(null == order){

        }
        return order;
    }

    /**
     * 主数据源新增一条order
     * @param input
     */
    @PostMapping("/insertOrderToPrimaryDataSource")
    public int insertOrderToPrimaryDataSource(@RequestBody User input){
        return orderMapperByXmlPrimary.insert(input);
    }

    /**
     * 第二数据源新增一条order
     * @param input
     */
    @PostMapping("/insertOrderToAssistDataSource")
    public int insertOrderToAssistDataSource(@RequestBody User input){
        return orderMapperByXmlAssist.insert(input);
    }

    /**
     * 主数据源更新一条order
     * @param input
     */
    @PutMapping("/updateOrderById")
    public int  updateOrderById(@RequestBody User input){
        return orderMapperByXmlPrimary.update(input);
    }

    /**
     * 主数据源根据id删除user
     * @param id
     * @return
     */
    @DeleteMapping("/deleteUserById/{id}")
    public int deleteUserById(@PathVariable("id") Long id){
        return orderMapperByXmlPrimary.delete(id);
    }


    /**
     * 主数据源测试声明式事务
     * @param input
     * @return
     */
    @Transactional
    @PostMapping("/transactionTest")
    public int transactionTest(@RequestBody User input){
        input.setCreator("zuozuo");
        orderMapperByXmlPrimary.insert(input);
        /*
        由于下面这行代码会抛异常，如果不在方法上添加@Transactional注解，则上面一条数据能够成功插入到数据库，如果添加
        了改注解，则上面一条数据不会插入到数据库，这样就保证了操作的原子性
         */
        System.out.println(1 / 0);
        input.setCreator("");
        orderMapperByXmlPrimary.insert(input);
        return 1;
    }
}
