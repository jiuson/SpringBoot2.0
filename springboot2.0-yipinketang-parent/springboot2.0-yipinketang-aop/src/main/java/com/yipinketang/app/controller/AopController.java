package com.yipinketang.app.controller;

import com.yipinketang.app.generalResponse.GeneralResponse;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopController {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Object handleException(MissingServletRequestParameterException ex){
        return new GeneralResponse(-100, ex.toString(), null);
    }

    @GetMapping("/getAopInfo")
    public Object getAopInfo(@RequestParam("name") String name){
        return new GeneralResponse(0, "OK", "this is aop info:" + name);
    }

    @GetMapping("/getError")
    public Object getError(){

        return 1 / 0;
    }
}
