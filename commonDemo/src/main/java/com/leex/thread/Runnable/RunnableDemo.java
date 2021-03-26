package com.leex.thread.Runnable;

import org.junit.Test;

/**
 * @Author : 86167
 * @Description : ThreadDemo 2021/3/25 20:53 86167
 */
public class RunnableDemo {

    @Test
    public void test(){

        Thread threadA = new Thread(new MyThread("线程A"));
        Thread threadB = new Thread(new MyThread("线程B"));
        Thread threadC = new Thread(new MyThread("线程C"));

        threadA.start();
        threadB.start();
        threadC.start();

    }

}
