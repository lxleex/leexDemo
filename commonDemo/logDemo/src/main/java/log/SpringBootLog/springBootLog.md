# SpringBoot中的Log

​	springboot框架在企业中的使用越来越普遍，springboot日志也是开发中常用的日志系统。springboot默认就是使用SLF4J作为日志门面，logback作为日志实现来记录日志。



##  SpringBoot中的日志设计

springboot中的日志

```xml
<dependency> 
	<artifactId>spring-boot-starter-logging</artifactId> 
	<groupId>org.springframework.boot</groupId> 
</dependency> 
```

依赖关系图![image-20210309213049660](F:/gitlocalRepository/imageRepository/default/springBootLog/image-20210309213049660.png)

总结：

1. springboot 底层默认使用logback作为日志实现。

2. 使用了SLF4J作为日志门面

3. 将JUL也转换成slf4j

4. 也可以使用log4j2作为日志门面，但是最终也是通过slf4j调用logback



##  SpringBoot日志使用

```java
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
```





### 使用application.properties配置日志

使用application.properties直接配置日志信息，这种方式有很大的局限性不常用。

```properties
# 自定义日志目录=级别
logging.level.com.leex=trace

#在控制台输出的日志的格式 同logback
logging.pattern.console=%d{yyyy-MM-dd} [%thread][%-5level]%logger{50}-%msg%n
#指定文件中日志输出的格式
# logging.file.name=D:/logs/springboot.log
logging.pattern.file=%d{yyyy-MM-dd}[%thread]%-5level%logger{50}-%msg%n
```



### 指定日志配置

给类路径下放上每个日志框架自己的配置文件；SpringBoot就不使用默认配置的了

| **日志框架** | **配置文件**                     |
| ------------ | -------------------------------- |
| Logback      | logback-spring.xml , logback.xml |
| Log4j2       | log4j2-spring.xml ， log4j2.xml  |
| JUL          | logging.properties               |

logback.xml：直接就被日志框架识别了，logback-spring.xml由SpringBoot识别配置



直接将配置文件放入 resource 目录下，配置信息见对应日志框架详解

---





### 使用SpringBoot解析日志配置

logback-spring.xml：由SpringBoot解析日志配置，可以指定当前环境，配置不同的日志方式

下文logback-spring.xml配置，注意`springProfile`包裹的部分就是指定环境才会生效的配置代码

```xml
<springProfile name="dev">
	······
</springProfile>
```



```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <property name="pattern" value="[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c %M %L [%thread] -------- %m %n"/>

    <!--控制台日志输出的 appender-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--控制输出流对象 默认 System.out 改为 System.err-->
        <target>System.err</target>
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <springProfile name="dev">
                <pattern>${pattern}</pattern>
            </springProfile>
            <springProfile name="pro">
                <pattern>[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c %M %L [%thread] xxxxxxxx %m %n</pattern>
            </springProfile>
        </encoder>
    </appender>

    <!--自定义 looger 对象
        additivity="false" 自定义 logger 对象是否继承 rootLogger
     -->
    <springProfile name="dev">
    <logger name="com.leex" level="info" additivity="false">
        <appender-ref ref="console"/>
    </logger>
    </springProfile>
    <springProfile name="pro">
        <logger name="com.leex" level="error" additivity="false">
            <appender-ref ref="console"/>
        </logger>
    </springProfile>
</configuration>
```

测试

application.properties 中 配置 spring.profiles.active=dev 日志：

```tex
[ERROR] 2021-03-09 21:50:04.808 com.leex.springbootlog.SpringbootlogApplicationTests contextLoads 16 [main] -------- error 
[WARN ] 2021-03-09 21:50:04.809 com.leex.springbootlog.SpringbootlogApplicationTests contextLoads 17 [main] -------- warn 
[INFO ] 2021-03-09 21:50:04.809 com.leex.springbootlog.SpringbootlogApplicationTests contextLoads 18 [main] -------- info 
```

application.properties 中 配置 spring.profiles.active=pro 日志：

```tex
[ERROR] 2021-03-09 21:47:35.023 com.leex.springbootlog.SpringbootlogApplicationTests contextLoads 16 [main] xxxxxxxx error 
```



### 将日志切换为log4j2

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <!--排除logback-->
        <exclusion>
            <artifactId>spring-boot-starter-logging</artifactId>
            <groupId>org.springframework.boot</groupId>
        </exclusion>
    </exclusions>
</dependency>
<!-- 添加log4j2 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>

去掉
<dependency> 
	<artifactId>spring-boot-starter-logging</artifactId> 
	<groupId>org.springframework.boot</groupId> 
</dependency> 
```

测试：

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

日志输出到文件中