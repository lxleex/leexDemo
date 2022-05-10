package com.leex.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext 工具类
 *
 * @Author : leex
 * @Description : ApplicationContextUtil 2022/4/7 23:28 leex
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    /**
     * 上下文对象
     */
    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext对象
     *
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过beanName获取bean
     *
     * @param beanName bean name
     * @return 获取的bean
     */
    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    /**
     * 通过class获取bean
     *
     * @param clazz class
     * @param <T>   对象泛型
     * @return      获取的bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过beanName class获取bean
     *
     * @param beanName bean name
     * @param clazz class
     * @param <T> 对象泛型
     * @return 获取的bean
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return getApplicationContext().getBean(beanName, clazz);
    }

    /**
     * @see ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     **/
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }
}
