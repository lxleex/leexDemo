package com.leex;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author : 86167
 * @Description : ProxyInvocationHandler 2021/3/29 11:25 86167
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    //生成代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    // proxy : 代理类
    // method : 代理类的调用处理程序的方法对象.
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 被代理对象执行前
        doSomethingBeforeInvoke();
        // 执行被代理的类
        // invoke方法注意target参数一定要有，否则java.lang.IllegalArgumentException: object is not an instance of declaring class
        Object invoke = method.invoke(target, args);
        // 被代理对象执行
        doSomethingAfterInvoke();

        return invoke;
    }

    private void doSomethingAfterInvoke() {
        System.out.println("代理对象执行结束");
    }

    private void doSomethingBeforeInvoke() {
        System.out.println("开始执行代理对象");
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
