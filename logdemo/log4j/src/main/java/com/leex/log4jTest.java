package com.leex;


import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * @Author : 86167
 * @Description : log4jTest 2021/3/8 22:36 86167
 */
public class log4jTest {

    private static final Logger logger = Logger.getLogger(log4jTest.class);

    @Test
    public void helloLog4j2() {
        for (int i = 0; i < 10000; i++) {
            logger.error("helloLog4j2 error");
            logger.warn("helloLog4j2 warn");
            logger.info("helloLog4j2 info");
            logger.debug("helloLog4j2 debug");
            logger.trace("helloLog4j2 trace");
        }
    }
}
