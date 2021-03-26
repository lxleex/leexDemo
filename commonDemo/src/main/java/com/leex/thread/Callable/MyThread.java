package com.leex.thread.Callable;

import java.util.concurrent.Callable;

/**
 * @Author : 86167
 * @Description : MyThread 2021/3/25 21:59 86167
 */
public class MyThread implements Callable<String> {
    private String threadName;
    public MyThread(String threadName) {
        this.threadName = threadName;
    }
    @Override
    public String call() throws Exception {
        for ( int x = 0 ; x < 10 ; x ++ ) {
            System.out.println(this.threadName + "运行，x = " + x);
        }
        return threadName + "执行完毕！";
    }
}
