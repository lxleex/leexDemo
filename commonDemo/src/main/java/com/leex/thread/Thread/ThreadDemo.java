package com.leex.thread.Thread;

import org.junit.Test;

/**
 * @Author : 86167
 * @Description : ThreadDemo 2021/3/25 20:53 86167
 */
public class ThreadDemo {

    @Test
    public void test(){

        MyThread a = new MyThread("线程A");
        MyThread b = new MyThread("线程B");
        MyThread c = new MyThread("线程C");

        a.start();
        b.start();
        c.start();

        System.out.println("主线程结束");
        /*try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");*/
    }



}
