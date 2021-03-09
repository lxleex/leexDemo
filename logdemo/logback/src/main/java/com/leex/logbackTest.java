package com.leex;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author : 86167
 * @Description : logbankTest 2021/3/8 0:08 86167
 */
public class logbackTest {

    public static final Logger LOGGER =
            LoggerFactory.getLogger(logbackTest.class);

    @Test
    public void helloLogback(){

            LOGGER.error("error");
            LOGGER.warn("warn");
            LOGGER.info("info");
            LOGGER.debug("debug");
            LOGGER.trace("trace");

        // 默认输出了debug级别以上的信息
    }

}
