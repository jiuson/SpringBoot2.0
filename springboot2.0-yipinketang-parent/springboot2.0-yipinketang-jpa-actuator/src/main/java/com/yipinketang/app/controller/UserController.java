package com.yipinketang.app.controller;

import com.yipinketang.app.entity.User;
import com.yipinketang.app.exception.ControllerException;
import com.yipinketang.app.repository.UserRepository;
import com.yipinketang.app.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler(ControllerException.class)
    public GeneralResponse handleException(ControllerException ex){
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setCode(ex.getErrorCode());
        generalResponse.setMessage(ex.getMessage());
        return generalResponse;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    @PostMapping("/createUser")
    public GeneralResponse createUser(@RequestBody Map input){
        User user = new User();
        user.setNumber(input.get("number") == null ? 1 : (Integer) input.get("number"));
        user.setCreator(StringUtils.isEmpty(input.get("creator")) ? "admin" : (String) input.get("creator"));
        user.setOrderName(StringUtils.isEmpty(input.get("orderName")) ? "adminOrder" : (String) input.get(
                "orderName"));
        user.setOrderType(User.OrderType.BUSINESS);
        user.setCreateDate(new Date());
        user.setModifyDate(new Date());
        user = userRepository.save(user);

        return new GeneralResponse(0, "ok", user);
    }

    @Transactional(readOnly = true, isolation = Isolation.DEFAULT)
    @GetMapping("/getUserByNumber/{number}")
    public GeneralResponse getUserByNumber(@PathVariable("number") Long number){
        if (null == number){
            throw new ControllerException("the special number must not be null:[number:" + number + "]", -1001);
        }
        Optional<User> userOptional = userRepository.findById(number);
        if (!userOptional.isPresent()){
            throw new ControllerException("the special number is not found user data[number:" + number + "]", -1002);
        }
        User user = userOptional.get();
        if(null == user){
            throw new ControllerException("the special number is not found user data[number:" + number + "]", -1002);
        }
        return new GeneralResponse(0, "ok", user);
    }

    @Transactional(readOnly = true, isolation = Isolation.DEFAULT)
    @PostMapping("/modifyUserByNumber/{number}")
    public GeneralResponse modifyUserByNumber(@PathVariable("number") Long number, @RequestBody Map input){
        if (null == number){
            throw new ControllerException("the special number must not be null:[number:" + number + "]", -1001);
        }
        User user = userRepository.getOne(number);
        //User user = userRepository.findById(number).get();//java 8新特性Optional
        if(null == user){
            throw new ControllerException("the special number is not found user data[number:" + number + "]", -1002);
        }
        user.setCreator(input.get("creator") == null ? null : (String) input.get("creator"));
        userRepository.save(user);

        return new GeneralResponse(0, "ok", user);
    }
}
