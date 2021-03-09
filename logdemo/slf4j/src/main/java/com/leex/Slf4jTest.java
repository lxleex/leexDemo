package com.leex;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author : 86167
 * @Description : Slf4jTest 2021/3/7 17:49 86167
 */
public class Slf4jTest {


    public static final Logger LOGGER =
            LoggerFactory.getLogger(Slf4jTest.class);

    /**
     *  快速入门
     **/
    @Test
    public void helloSlf4j() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        // 默认输出了info级别以上的信息


        // 使用站位符输出信息
        String str = "我是字符串";
        Integer i = 12;
        // 使用{} 作为占位符输出变量信息
        LOGGER.info("输出一行日志，{}", str);
        // 多传一个变量会怎样？  直接别忽略
        LOGGER.info("多传一个变量会怎样？，{}", str, i);
        // 少传一个变量会怎样？  多余的占位符输出了null
        LOGGER.info("少传一个变量会怎样？，第一个：{}, 第二个；{}", str);


        // 将系统异常信息输出
        try {
            int j = i / 0;
        }catch (Exception e){
            LOGGER.error("出异常啦出异常啦", e);
        }
    }

    @Test
    public void bridgingTest() {

        org.apache.log4j.Logger logger4j =
                org.apache.log4j.Logger.getLogger(Slf4jTest.class);

        logger4j.info("一行日志");
    }

}
