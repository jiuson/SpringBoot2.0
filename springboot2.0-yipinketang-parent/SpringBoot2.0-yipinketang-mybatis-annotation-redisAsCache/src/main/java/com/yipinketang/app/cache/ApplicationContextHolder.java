package com.yipinketang.app.cache;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取一个全局的Spring上下文 applicationContext
 *
 * @create by zuowj 20181122
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        applicationContext = ctx;
    }

    /**
     * Get applicationContext from everywhere
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * Get bean by class
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * Get bean by class name
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }
}
