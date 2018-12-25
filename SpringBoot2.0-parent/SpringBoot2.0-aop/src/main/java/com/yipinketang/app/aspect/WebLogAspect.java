package com.yipinketang.app.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.yipinketang.app.controller.*.*(..))")//这个表达式表示controller包下的所有类的所有public方法
    public void webLog(){}

    @Before("webLog()")//指定切点
    public void deBefore(JoinPoint joinPoint){
        logger.info("-------------------deBefore begin------------------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("IP:" + request.getRemoteAddr());
        logger.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));
        logger.info("-------------------deBefore end------------------");
    }

    @AfterReturning(returning = "obj", pointcut = "webLog()")
    public void doAfterReturning(Object obj){
        logger.info("-------------------doAfterReturning begin------------------");
        logger.info("方法返回值:" + obj);
        logger.info("-------------------doAfterReturning end------------------");
    }

    @AfterThrowing("webLog()")
    public void doAfterThrowing(JoinPoint joinPoint){
        logger.info("-------------------doAfterThrowing begin------------------");
        logger.info("方法异常时执行");
        logger.info("-------------------doAfterThrowing end------------------");
    }

    @After("webLog()")
    public void doAfter(JoinPoint joinPoint){
        logger.info("-------------------doAfter begin------------------");
        logger.info("方法执行完后执行");
        logger.info("-------------------doAfter end------------------");
    }

//    @Around("webLog()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint){
        logger.info("-------------------doAround begin------------------");
        try {
            Object object = proceedingJoinPoint.proceed();
            logger.info("方法环绕proceed，结果是:" + object);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("-------------------doAround end------------------");
    }
}
