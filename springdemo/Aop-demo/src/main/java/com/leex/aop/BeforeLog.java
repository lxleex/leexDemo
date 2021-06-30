package com.leex.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author : 86167
 * @Description : BeforeLog 2021/3/29 15:21 86167
 */
public class BeforeLog implements MethodBeforeAdvice {
    //method : 要执行的目标对象的方法
    //objects : 被调用的方法的参数
    //Object : 目标对象
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getSimpleName() + " 的 " + method.getName() + "方法, 执行之前"
                + "BeforeLog执行啦");
    }
}
