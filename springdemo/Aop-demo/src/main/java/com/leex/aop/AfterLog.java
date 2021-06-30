package com.leex.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Author : 86167
 * @Description : AfterLog 2021/3/29 15:28 86167
 */
public class AfterLog implements AfterReturningAdvice{
    //returnValue 返回值
    //method被调用的方法
    //args 被调用的方法的对象的参数
    //target 被调用的目标对象
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterLog执行了" + target.getClass().getName()
                +"的"+method.getName()+"方法,"
                +"返回值："+ returnValue);
    }
}
