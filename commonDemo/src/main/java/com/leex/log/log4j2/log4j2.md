# log4j2

Apache Log4j 2是对Log4j的升级版，参考了logback的一些优秀的设计，并且修复了一些问题，因此带来了一些重大的提升，主要有：

- 异常处理，在logback中，Appender中的异常不会被应用感知到，但是在log4j2中，提供了一些异常处理机制。

- 性能提升， log4j2相较于log4j 和logback都具有很明显的性能提升，后面会有官方测试的数据。

- 自动重载配置，参考了logback的设计，当然会提供自动刷新参数配置，最实用的就是我们在生产上可以动态的修改日志的级别而不需要重启应用。

- 无垃圾机制，log4j2在大部分情况下，都可以使用其设计的一套无垃圾机制，避免频繁的日志收集导致的jvm gc。

官网： https://logging.apache.org/log4j/2.x/



##  Log4j2入门

输出日志到控制台

引入依赖

```xml
<!--使用slf4j 作为日志门面-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.26</version>
</dependency>
<!--使用 log4j2 的适配器进行绑定-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-slf4j-impl</artifactId>
    <version>2.9.1</version>
</dependency>
<!--log4j2日志门面-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.11.1</version>
</dependency>
<!--log4j2 日志实现-->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.11.1</version>
</dependency>
```

基础配置 log4j2默认加载classpath下的 log4j2.xml 文件中的配置。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
    status="warn" 日志框架本身的输出日志级别
    monitorInterval="5" 自动加载配置文件的间隔时间，不低于 5 秒
-->
<Configuration status="debug" monitorInterval="5">

    <!--日志处理-->
    <Appenders>
        <!--控制台输出 appender-->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] [%-5level] %c{36}:%L --- %m%n" />
        </Console>
    </Appenders>

    <!--logger 定义-->
    <Loggers>
        <!--使用 rootLogger 配置 日志级别 level="trace"-->
        <Root level="trace">
            <!--指定日志使用的处理器-->
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

测试代码

```java
public class log4j2Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(log4j2Test.class);
    
    @Test
    public void helloLog4j2() {
        LOGGER.error("helloLog4j2 error");
        LOGGER.warn("helloLog4j2 warn");
        LOGGER.info("helloLog4j2 info");
        LOGGER.debug("helloLog4j2 debug");
        LOGGER.trace("helloLog4j2 trace");
    }
}
```



## 配置详解

### FileAppender配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
    status="warn" 日志框架本身的输出日志级别
    monitorInterval="5" 自动加载配置文件的间隔时间，不低于 5 秒
-->
<Configuration status="debug" monitorInterval="5">

    <!--
        集中配置属性进行管理
        使用时通过:${name}
    -->
    <properties>
        <property name="LOG_HOME">src/main/java/com/leex/logs</property>
    </properties>

    <!--日志处理-->
    <Appenders>
        <!--控制台输出 appender-->
        <Console name="Console" target="SYSTEM_ERR">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] [%-5level] %c{36}:%L --- %m%n" />
        </Console>

        <!--日志文件输出 appender-->
        <File name="file" fileName="${LOG_HOME}/myfile.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %m%n" />
        </File>

        <!--使用随机读写刘的日志文件输出 appender，性能提高-->
        <RandomAccessFile name="accessFile" fileName="${LOG_HOME}/myAcclog.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %m%n" />
        </RandomAccessFile>

        <!--按照一定规则拆分的日志文件的 appender-->
        <!-- src/main/java/com/leex/logs/2021-03-09/myrollog-2021-03-09-00-20-31.log -->
        <RollingFile name="rollingFile" fileName="${LOG_HOME}/myRollog.log"
                     filePattern="${LOG_HOME}/$${date:yyyy-MM-dd}/myrollog-%d{yyyy-MM-dd-HH-mm}-%i.log">
            <!--日志级别过滤器-->
            <ThresholdFilter level="debug" onMatch="ACCEPT" onMismatch="DENY" />
            <!--日志消息格式-->
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %msg%n" />
            <Policies>
                <!--在系统启动时，出发拆分规则，生产一个新的日志文件-->
                <OnStartupTriggeringPolicy />
                <!--按照文件大小拆分，10MB -->
                <SizeBasedTriggeringPolicy size="10 MB" />
                <!--按照时间节点拆分，规则根据filePattern定义的-->
                <TimeBasedTriggeringPolicy />
            </Policies>
            <!--在同一个目录下，文件的个数限定为 30 个，超过进行覆盖-->
            <DefaultRolloverStrategy max="30" />
        </RollingFile>

    </Appenders>

    <!--logger 定义-->
    <Loggers>
        <!--使用 rootLogger 配置 日志级别 level="trace"-->
        <Root level="trace">
            <!--指定日志使用的处理器-->
            <!--<AppenderRef ref="Console" />-->
            <AppenderRef ref="rollingFile" />
        </Root>
    </Loggers>
</Configuration>
```





### Log4j2异步日志

**异步日志**

log4j2最大的特点就是异步日志，其性能的提升主要也是从异步日志中受益，我们来看看如何使用log4j2的异步日志。

#### 同步日志

![image-20210309003109868](F:/gitlocalRepository/imageRepository/default/log4j2/image-20210309003109868.png)

#### 异步日志

![image-20210309003041739](F:/gitlocalRepository/imageRepository/default/log4j2/image-20210309003041739.png)



Log4j2提供了两种实现日志的方式，一个是通过AsyncAppender，一个是通过AsyncLogger，分别对应前面我们说的Appender组件和Logger组件。

注意：配置异步日志需要添加依赖

```xml
<!--异步日志依赖-->
<dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.3.4</version>
</dependency>
```



#### AsyncAppender方式实现异步日志



1. `Appenders` 中增加 `Async` 标签

2. `Loggers` 中使用 `Async`

   

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
    status="warn" 日志框架本身的输出日志级别
    monitorInterval="5" 自动加载配置文件的间隔时间，不低于 5 秒
-->
<Configuration status="debug" monitorInterval="5">

    <properties>
        <property name="LOG_HOME">src/main/java/com/leex/logs</property>
    </properties>

    <!--日志处理-->
    <Appenders>

        <!--日志文件输出 appender-->
        <File name="file" fileName="${LOG_HOME}/myfile.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %m%n" />
        </File>
       	<!--异步日志-->
        <Async name="Async">
            <AppenderRef ref="file"/>
        </Async>
        
    </Appenders>

    <!--logger 定义-->
    <Loggers>
        <!--使用 rootLogger 配置 日志级别 level="trace"-->
        <Root level="trace">
            <!--指定日志使用的处理器-->
            <AppenderRef ref="Async" />
        </Root>
    </Loggers>
</Configuration>
```





#### AsyncLogger方式实现异步日志

AsyncLogger是官方推荐的异步方式。它可以使得调用Logger.log返回的更快。分为：全局异步和混合异步。



1. **全局异步**，所有的日志都异步的记录，在配置文件上不用做任何改动，只需要添加一个 `log4j2.component.properties` 配置；

```properties
Log4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
```



2. **混合异步**，你可以在应用中同时使用同步日志和异步日志，这使得日志的配置方式更加灵活。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
    status="warn" 日志框架本身的输出日志级别
    monitorInterval="5" 自动加载配置文件的间隔时间，不低于 5 秒
-->
<Configuration status="debug" monitorInterval="5">

    <properties>
        <property name="LOG_HOME">src/main/java/com/leex/logs</property>
    </properties>

    <!--日志处理-->
    <Appenders>

        <!--日志文件输出 appender-->
        <File name="file" fileName="${LOG_HOME}/myfile.log">
            <PatternLayout pattern="[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5level] %l %c{36} - %m%n" />
        </File>

    </Appenders>

    <!--logger 定义-->
    <Loggers>
        <!--自定义异步 logger 对象
            includeLocation="false" 关闭日志记录的行号信息，会影响性能
            additivity="false" 不在继承 rootlogger 对象
        -->
        <AsyncLogger name="com.leex" level="trace" includeLocation="false" additivity="false">
            <AppenderRef ref="file"/>
        </AsyncLogger>

        <!--使用 rootLogger 配置 日志级别 level="trace"-->
        <Root level="trace">
            <!--指定日志使用的处理器-->
            <AppenderRef ref="file" />
        </Root>
    </Loggers>
</Configuration>
```

自定义的 com.leex 就是异步日志，而root是同步日志



使用异步日志需要**注意的问题**： 

1. 如果使用异步日志，AsyncAppender、AsyncLogger和全局日志，不要同时出现。性能会和AsyncAppender一致，降至最低。

2. 设置includeLocation=false ，打印位置信息会急剧降低异步日志的性能，比同步日志还要慢

