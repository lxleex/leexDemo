package com.leex.aop;

/**
 * @Author : 86167
 * @Description : MyPointCut 2021/3/29 15:40 86167
 */
public class MyPointCut {

    public void before(){
        System.out.println("---------方法执行前---------");
    }
    public void after(){
        System.out.println("---------方法执行后---------");
    }

}
