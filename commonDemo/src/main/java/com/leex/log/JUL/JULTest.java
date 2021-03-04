package com.leex.log.JUL;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * @Author : 86167
 * @Description : JULTest 2021/3/1 22:12 86167
 */
public class JULTest {


    @Test
    public void helloJULTest() {
        // 1.获取日志记录器
        Logger logger = Logger.getLogger("com.leex.log.JUL.JULTest");

        //2.日志记录
        logger.info("hello JUL");
        // 通过日志级别输出
        logger.log(Level.INFO, "通过日志级别输出");
        // 通过占位符输出
        String time = "2021年3月1日22:20:23";
        int i = Integer.MAX_VALUE;
        logger.log(Level.INFO, "通过占位符输出一个变量日志信息：{0}", time);
        logger.log(Level.INFO, "通过占位符输出多个变量日志信息：{0}, {1}", new Object[]{time, i});
        logger.log(Level.INFO, "通过占位符输出日志信息：{0} 数量不匹配自动舍弃", new Object[]{time, i});
        logger.log(Level.INFO, "通过占位符输出多个变量日志信息：{0}, {1}，数量不匹配直接输出", new Object[]{time});
    }

    @Test
    public void levelTest() throws IOException {
        // 1.获取日志记录器
        Logger logger = Logger.getLogger("com.leex.log.JUL.JULTest");

        // 2.日志记录输出
        doLog(logger, "默认日志级别测试: ");

        // 一、自定义日志级别
        // a.关闭系统默认配置
        logger.setUseParentHandlers(false);
        // b.创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // c.创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // d.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);
        // e.设置日志级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        // 二、输出到日志文件
        FileHandler fileHandler = new FileHandler("src/main/java/com/leex/log/JUL/jul.log");
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
        // 2.日志记录输出
        doLog(logger, "自定义日志级别测试: ");

    }

    @Test
    public void parentTest(){
        // 日志记录器对象父子关系
        Logger logger1 = Logger.getLogger("com.leex.log.JUL");
        Logger logger2 = Logger.getLogger("com.leex.log");
        // 结果为 true 证明JUL的父子关系为 name的层级关系。 修改Logger logger2 = Logger.getLogger("com.leex2.log");返回false
        System.out.println(logger1.getParent() == logger2);
        System.out.println(logger1.getParent().getName());
        // 所有日志记录器对象的顶级父元素 class为java.util.logging.LogManager$RootLogger name为""
        System.out.println("logger2 parent:" + logger2.getParent() + "，name：" + logger2.getParent().getName());

        // 一、自定义日志级别
        // a.关闭系统默认配置
        logger2.setUseParentHandlers(false);
        // b.创建handler对象
        ConsoleHandler consoleHandler = new ConsoleHandler();
        // c.创建formatter对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // d.进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);
        // e.设置日志级别
        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        doLog(logger1, "JUL父子关系测试: ");
    }

    private void doLog(Logger logger, String log) {
        logger.severe(log + "severe");
        logger.warning(log + "warning");
        logger.info(log + "info");
        logger.config(log + "config");
        logger.fine(log + "fine");
        logger.finer(log + "finer");
        logger.finest(log + "finest");
    }

    @Test
    public void configTest() throws IOException {
        // 读取配置文件，通过类加载器
        InputStream ins = JULTest.class.getClassLoader().getResourceAsStream("JUL_logging.properties");
        // 创建LogManager
        LogManager logManager = LogManager.getLogManager();
        // 通过LogManager加载配置文件
        logManager.readConfiguration(ins);

        // 创建日志记录器
        Logger logger = Logger.getLogger("com.leex.log");

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");


        Logger logger2 = Logger.getLogger("test");

        logger2.severe("severe test");
        logger2.warning("warning test");
        logger2.info("info test");
        logger2.config("config test");
        logger2.fine("fine test");
        logger2.finer("finer test");
        logger2.finest("finest test");

    }



}
