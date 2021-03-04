package com.leex.log.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.DecimalFormat;

/**
 * @Author : 86167
 * @Description : Log4jTest2 2021/3/2 23:47 86167
 */
public class Log4jTest {


    @Test
    public void helloLog4j(){

        // 创建日志记录器对象
        Logger logger = Logger.getLogger(Log4jTest.class);

        // 日志级别
        logger.fatal("fatal"); // 严重错误，一般会造成系统崩溃并终止运行

        logger.error("error"); // 错误信息，不会影响系统运行
        logger.warn("warn");   // 警告信息，可能会发生问题
        logger.info("info");   // 运行信息，数据连接、网络连接、IO 操作等等
        logger.debug("debug"); // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等

        logger.trace("trace"); // 追踪信息，记录程序所有的流程信息

    }



}
