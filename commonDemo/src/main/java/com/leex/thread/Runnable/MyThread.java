package com.leex.thread.Runnable;

/**
 * @Author : 86167
 * @Description : MyThread 2021/3/25 20:51 86167
 */
public class MyThread implements Runnable{
    private String threadName;

    public MyThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {//线程的主体方法
        for(int x = 0; x < 10 ; x++) {
            System.out.println(this.threadName + "运行，x = " + x);
            //StaticInt.add(threadName);
        }
    }
}
