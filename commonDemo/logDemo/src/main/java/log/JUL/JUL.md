# JUL

JUL全称Java util Logging是java原生的日志框架，使用时不需要另外引用第三方类库，相对其他日志框架使用方便，学习简单，能够在小型应用中灵活使用



## JUL入门

```properties
# RootLogger 顶级父元素指定的默认处理器为：ConsoleHandler
handlers= java.util.logging.ConsoleHandler

# 向控制台输出的 handler 对象
# 指定 handler 对象的日志级别
java.util.logging.ConsoleHandler.level = ALL
# 指定 handler 对象的日志消息格式对象
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
# 指定 handler 对象的字符集
java.util.logging.ConsoleHandler.encoding = UTF-8

# 指定日志消息格式
java.util.logging.SimpleFormatter.format = %4$s: %5$s [%1$tc]%n
```

```java
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
```



## JUL架构



![Image](F:/gitlocalRepository/imageRepository/default/JUL/Image.png)

- Loggers：被称为记录器，应用程序通过获取Logger对象，调用其API来来发布日志信息。Logger通常时应用程序访问日志系统的入口程序。

- Appenders：也被称为Handlers，每个Logger都会关联一组Handlers，Logger会将日志交给关联Handlers处理，由Handlers负责将日志做记录。Handlers在此是一个抽象，其具体的实现决定了日志记录的位置可以是控制台、文件、网络上的其他日志服务或操作系统日志等。

- Layouts：也被称为Formatters，它负责对日志事件中的数据进行转换和格式化。Layouts决定了数据在一条日志记录中的最终形式。

- Level：每条日志消息都有一个关联的日志级别。该级别粗略指导了日志消息的重要性和紧迫，我可以将Level和Loggers，Appenders做关联以便于我们过滤消息。

- Filters：过滤器，根据需要定制哪些信息会被记录



用户使用Logger来进行日志记录，Logger持有若干个Handler，日志的输出操作是由Handler完成的。在Handler在输出日志前，会经过Filter的过滤，判断哪些日志级别过滤放行哪些拦截，Handler会将日志内容输出到指定位置（日志文件、控制台等）。Handler在输出日志时会使用Layout，将输出内容进行排版





## 自定义日志级别测试

```java
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
    FileHandler fileHandler = new FileHandler("src/main/java/com/leex/jul.log");
    fileHandler.setFormatter(simpleFormatter);
    logger.addHandler(fileHandler);
    // 2.日志记录输出
    doLog(logger, "自定义日志级别测试: ");

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
```



## Logger之间的父子关系

```java
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
```



## 日志的配置文件

resource 目录下 logging.properties



```java
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
```

```properties
# RootLogger 顶级父元素指定的默认处理器为：ConsoleHandler
handlers= java.util.logging.ConsoleHandler

# RootLogger 顶级父元素默认的日志级别为：ALL
.level= ALL

# 自定义 Logger 使用
com.leex.log.handlers = java.util.logging.ConsoleHandler
com.leex.log.level = CONFIG

# 关闭默认配置
com.leex.log.useParentHanlders = false


# 向日志文件输出的 handler 对象
# 指定日志文件路径 /logs/java0.log
java.util.logging.FileHandler.pattern = src/main/java/com/leex/log/JUL/java%u.log
# 指定日志文件内容大小
java.util.logging.FileHandler.limit = 50000
# 指定日志文件数量
java.util.logging.FileHandler.count = 1
# 指定 handler 对象日志消息格式对象
java.util.logging.FileHandler.formatter = java.util.logging.SimpleFormatter
# 指定以追加方式添加日志内容 默认为false
java.util.logging.FileHandler.append = true


# 向控制台输出的 handler 对象
# 指定 handler 对象的日志级别
java.util.logging.ConsoleHandler.level = ALL
# 指定 handler 对象的日志消息格式对象
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
# 指定 handler 对象的字符集
java.util.logging.ConsoleHandler.encoding = UTF-8

# 指定日志消息格式
java.util.logging.SimpleFormatter.format = %4$s: %5$s [%1$tc]%n
```