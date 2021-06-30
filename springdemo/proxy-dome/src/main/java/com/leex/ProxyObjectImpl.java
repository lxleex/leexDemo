package com.leex;

/**
 * @Author : 86167
 * @Description : 被代理对象 2021/3/29 11:31 86167
 */
public class ProxyObjectImpl implements ProxyObject {

    @Override
    public String process(String arg){
        System.out.println("ProxyObjectImpl process 执行了" + arg);
        return "我是返回值";
    }

}
