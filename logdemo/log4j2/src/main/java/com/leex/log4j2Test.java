package com.leex;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author : 86167
 * @Description : log4j2Test 2021/3/8 22:36 86167
 */
public class log4j2Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(log4j2Test.class);

    @Test
    public void helloLog4j2() {
        for (int i = 0; i < 10000; i++) {
            LOGGER.error("helloLog4j2 error");
            LOGGER.warn("helloLog4j2 warn");
            LOGGER.info("helloLog4j2 info");
            LOGGER.debug("helloLog4j2 debug");
            LOGGER.trace("helloLog4j2 trace");
        }
    }
}
