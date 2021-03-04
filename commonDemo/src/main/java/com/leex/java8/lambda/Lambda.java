package com.leex.java8.lambda;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;

/**
 * @Author : 86167
 * @Description : Lambda-2020/12/14 23:29-86167
 */
@Resource(mappedName = "Resource mappedName")
public class Lambda {

    private static Logger logger = LogManager.getLogger(Lambda.class);

    public static void main(String[] args) {


        /*
        String log4j = Lambda.class.getClassLoader().getResource("log4j.properties").getPath();
        System.out.println(log4j);
        PropertyConfigurator.configure(log4j);//加载.properties文件*/

        logger.info("123 [{}]", 1,2,3,4,5,7);

        //System.out.println("132");

    }

}
