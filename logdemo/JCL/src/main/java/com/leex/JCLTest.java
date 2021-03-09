package com.leex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @Author : 86167
 * @Description : JCLTest 2021/3/4 22:56 86167
 */
public class JCLTest {

    @Test
    public void helloJCL(){

        // 获取 log日志记录器对象
        Log log = LogFactory.getLog(JCLTest.class);
        // 日志记录输出
        log.info("hello jcl");


    }

}
