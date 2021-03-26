package com.leex.thread.Thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author : 86167
 * @Description : StaticInt 2021/3/25 21:00 86167
 */
public class StaticInt {

    private static int  i = 0;

    private static int  j = 1;

    private static final Lock REENTRANT_LOCK = new ReentrantLock();

    public static void add(String str){
        try{
            REENTRANT_LOCK.lock();
            i = i + 1;
            System.out.println(j++ + ":" + str + "执行：" + i);
        } finally {
            REENTRANT_LOCK.unlock();
        }
    }

}
