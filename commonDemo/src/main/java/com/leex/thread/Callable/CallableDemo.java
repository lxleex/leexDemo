package com.leex.thread.Callable;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author : 86167
 * @Description : ThreadDemo 2021/3/25 20:53 86167
 */
public class CallableDemo {

    @Test
    public void test() throws ExecutionException, InterruptedException {

        FutureTask futureTaskA = new FutureTask(new MyThread("线程A"));
        FutureTask futureTaskB = new FutureTask(new MyThread("线程B"));
        FutureTask futureTaskC = new FutureTask(new MyThread("线程C"));

        new Thread(futureTaskA).start();
        new Thread(futureTaskB).start();
        new Thread(futureTaskC).start();

        System.out.println(futureTaskA.get());
        System.out.println(futureTaskB.get());
        System.out.println(futureTaskC.get());
    }

}
