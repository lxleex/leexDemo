package com.leex.springbootlog;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootlogApplicationTests {

    public static final Logger LOGGER =
            LoggerFactory.getLogger(SpringbootlogApplicationTests.class);

    @Test
    void contextLoads() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        // 默认输出了info级别以上的信息
    }

}
