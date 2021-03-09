# logback

Logback是由log4j创始人设计的另一个开源日志组件，性能比log4j要好。

官方网站：https://logback.qos.ch/index.html



Logback主要分为三个模块：

logback-core：其它两个模块的基础模块

logback-classic：它是log4j的一个改良版本，同时它完整实现了slf4j API

logback-access：访问模块与Servlet容器集成提供通过Http来访问日志的功能



## logback入门

引入依赖

```xml
<!-- slf4j 日志门面 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.26</version>
</dependency>

<!-- logback-classic -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
    <scope>test</scope>
</dependency>
```

```java
//使用slf4j操作日志
@Test
public void helloLogback(){
    LOGGER.error("error");
    LOGGER.warn("warn");
    LOGGER.info("info");
    LOGGER.debug("debug");
    LOGGER.trace("trace");
    // 默认输出了debug级别以上的信息
}
```



##  logback配置

logback会依次读取以下类型配置文件：

- logback.groovy

- logback-test.xml

- logback.xml 如果均不存在会采用默认配置



logback组件之间的关系

1. Logger:日志的记录器，把它关联到应用的对应的context上后，主要用于存放日志对象，也可以定义日志类型、级别。

2. Appender:用于指定日志输出的目的地，目的地可以是控制台、文件、数据库等等。

3. Layout:负责把事件转换成字符串，格式化的日志信息的输出。在logback中Layout对象被封装在encoder中。



基础配置实例

### 输出到控制台

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!--
        配置集中管理属性
        我们可以直接改属性的 value 值
        格式：${name}
    -->
    <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度 %msg：日志消息，%n是换行符-->
    <property name="pattern" value="[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c %M %L [%thread] %m%n"/>
    <!--
    日志输出格式：
        %-5level
        %d{yyyy-MM-dd HH:mm:ss.SSS}日期
        %c类的完整名称
        %M为method
        %L为行号
        %thread线程名称
        %m或者%msg为信息
        %n换行
      -->

    <!--Appender: 设置日志信息的去向,常用的有以下几个 
	ch.qos.logback.core.ConsoleAppender (控制台)
	ch.qos.logback.core.rolling.RollingFileAppender (文件大小到达指定尺 寸的时候产生一个新文件)
	ch.qos.logback.core.FileAppender (文件)
 	-->
    <!--控制台日志输出的 appender-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--控制输出流对象 默认 System.out 改为 System.err-->
        <target>System.out</target>
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!-- 引入上文配置的日志格式 -->
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>

    <!--
    用来设置某一个包或者具体的某一个类的日志打印级别、以及指定<appender>。 
    <loger>仅有一个name属性，一个可选的level和一个可选的addtivity属性 name:用来指定受此logger约束的某一个包或者具体的某一个类。 
    level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF， 如果未设置此属性，那么当前logger将会继承上级的级别。
    additivity: 是否向上级loger传递打印信息。默认是true。 
    <logger>可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个 logger
    --> 
    <!--
    也是<logger>元素，但是它是根logger。默认debug level:用来设置打印级别，大小写无关：TRACE, DEBUG, INFO, WARN, ERROR, ALL 和 OFF， 
    <root>可以包含零个或多个<appender-ref>元素，标识这个appender将会添加到这个 logger。 
    -->
    <!--root logger 配置-->
    <!-- 打开所有的位置级别 -->
    <root level="ALL">
        <!-- 将上文定义的console控制台Appender添加进根目录 -->
        <appender-ref ref="console"/>
    </root>
</configuration>
```

执行入门案例的日志输出

```tex
21:14:45,946 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback-test.xml]
21:14:45,946 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Could NOT find resource [logback.groovy]
21:14:45,946 |-INFO in ch.qos.logback.classic.LoggerContext[default] - Found resource [logback.xml] at [file:/E:/java/workSpace/leexDemo/logdemo/logback/target/classes/logback.xml]
21:14:46,059 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - debug attribute not set
21:14:46,067 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - About to instantiate appender of type [ch.qos.logback.core.ConsoleAppender]
21:14:46,070 |-INFO in ch.qos.logback.core.joran.action.AppenderAction - Naming appender as [console]
21:14:46,120 |-INFO in ch.qos.logback.classic.joran.action.RootLoggerAction - Setting level of ROOT logger to ALL
21:14:46,120 |-INFO in ch.qos.logback.core.joran.action.AppenderRefAction - Attaching appender named [console] to Logger[ROOT]
21:14:46,121 |-ERROR in ch.qos.logback.core.joran.action.AppenderRefAction - Could not find an appender named [async]. Did you define it below instead of above in the configuration file?
21:14:46,121 |-ERROR in ch.qos.logback.core.joran.action.AppenderRefAction - See http://logback.qos.ch/codes.html#appender_order for more details.
21:14:46,121 |-INFO in ch.qos.logback.classic.joran.action.ConfigurationAction - End of configuration.
21:14:46,122 |-INFO in ch.qos.logback.classic.joran.JoranConfigurator@3a03464 - Registering current configuration as safe fallback point

[ERROR] 2021-03-08 21:14:46.128 com.leex.logbackTest helloLogback 18 [main] error
[WARN ] 2021-03-08 21:14:46.131 com.leex.logbackTest helloLogback 19 [main] warn
[INFO ] 2021-03-08 21:14:46.131 com.leex.logbackTest helloLogback 20 [main] info
[DEBUG] 2021-03-08 21:14:46.132 com.leex.logbackTest helloLogback 21 [main] debug
[TRACE] 2021-03-08 21:14:46.132 com.leex.logbackTest helloLogback 22 [main] trace

Process finished with exit code 0
```



### FileAppender配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!--
        配置集中管理属性
        我们可以直接改属性的 value 值
        格式：${name}
    -->
    <property name="pattern" value="[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c %M %L [%thread] %m%n"/>
    <!--
    日志输出格式：
        %-5level
        %d{yyyy-MM-dd HH:mm:ss.SSS}日期
        %c类的完整名称
        %M为method
        %L为行号
        %thread线程名称
        %m或者%msg为信息
        %n换行
      -->
    <!--定义日志文件保存路径属性-->
    <property name="log_dir" value="src/main/java/com/leex/logs"/>

    <!--控制台日志输出的 appender-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--控制输出流对象 默认 System.out 改为 System.err-->
        <target>System.out</target>
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!-- 引入上文配置的日志格式 -->
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>

    <!--日志文件输出的 appender-->
    <appender name="file" class="ch.qos.logback.core.FileAppender">
        <!--日志文件保存路径-->
        <file>${log_dir}/logback.log</file>
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${pattern}</pattern>
        </encoder>
    </appender>

    <!--日志拆分和归档压缩的 appender 对象-->
    <appender name="rollFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--日志文件保存路径-->
        <!--<file>${log_dir}/roll_logback.log</file>-->
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${pattern}</pattern>
        </encoder>
        <!--指定拆分规则-->
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--按照时间和压缩格式声明拆分的文件名-->
            <!-- %d{yyyy-MM-dd}表示按日拆分,-号分割 ；
                log%i 表示拆分的文件 .log1.gz   .log2.gz
                   .gz .zip表示压缩包-->
            <fileNamePattern>${log_dir}/rolling.%d{yyyy-MM-dd-hh-mm-ss}.log%i.zip</fileNamePattern>
            <!--按照文件大小拆分-->
            <maxFileSize>1MB</maxFileSize>
        </rollingPolicy>
    </appender>

    <!--root logger 配置-->
    <!-- 打开所有的位置级别 -->
    <root level="ALL">
        <!-- 将上文定义的console控制台Appender添加进根目录 -->
        <appender-ref ref="console"/>
        <appender-ref ref="file"/>
        <appender-ref ref="rollFile"/>
    </root>
</configuration>
```



测试代码

```java
@Test
public void helloLogback(){
    for (int i = 0; i < 10000; i++) {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
    }
    // 默认输出了debug级别以上的信息
}
```



更改拆分属性

```xml
<!-- 按每天/1MB文件拆分 -->
<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
    <!--按照时间和压缩格式声明拆分的文件名-->
    <fileNamePattern>${log_dir}/rolling.%d{yyyy-MM-dd}.log%i.zip</fileNamePattern>
    <!--按照文件大小拆分-->
    <maxFileSize>1MB</maxFileSize>
</rollingPolicy>
```

![image-20210308215211364](F:/gitlocalRepository/imageRepository/default/logback/image-20210308215211364.png)

```xml
<!-- 按每秒/1MB文件拆分 -->
<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
    <!--按照时间和压缩格式声明拆分的文件名-->
    <fileNamePattern>${log_dir}/rolling.%d{yyyy-MM-dd}.log%i.zip</fileNamePattern>
    <!--按照文件大小拆分-->
    <maxFileSize>1MB</maxFileSize>
</rollingPolicy>
```



![image-20210308215350397](F:/gitlocalRepository/imageRepository/default/logback/image-20210308215350397.png)



### Filter和异步日志配置

过滤器：在`appender`标签中加入`filter`过滤器标签，appender使用过滤器

```xml
<!--控制台日志输出的 appender-->
<appender name="console" class="ch.qos.logback.core.ConsoleAppender">
    .......
    <!--日志级别过滤器-->
    <filter class="ch.qos.logback.classic.filter.LevelFilter">
        <!-- 配置过滤的日志级别 -->
        <level>ERROR</level>
        <!-- 大于配置的日志级别则放行 -->
        <onMatch>ACCEPT</onMatch>
        <!-- 小于配置的日志级别则放行 -->
        <onMismatch>DENY</onMismatch>
    </filter>
</appender>
```



异步日志：相当于在`appender`和`logger`之间加一层`appender`作为异步操作

```xml
<!--异步日志-->
<appender name="async" class="ch.qos.logback.classic.AsyncAppender">
    <!--指定某个具体的 appender-->
    <appender-ref ref="rollFile"/>
</appender>
```



测试

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!--
        配置集中管理属性
        我们可以直接改属性的 value 值
        格式：${name}
    -->
    <property name="pattern" value="[%-5level] %d{yyyy-MM-dd HH:mm:ss.SSS} %c %M %L [%thread] %m%n"/>
    <!--
    日志输出格式：
        %-5level
        %d{yyyy-MM-dd HH:mm:ss.SSS}日期
        %c类的完整名称
        %M为method
        %L为行号
        %thread线程名称
        %m或者%msg为信息
        %n换行
      -->
    <!--定义日志文件保存路径属性-->
    <property name="log_dir" value="src/main/java/com/leex/logs"/>

    <!--控制台日志输出的 appender-->
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <!--控制输出流对象 默认 System.out 改为 System.err-->
        <target>System.out</target>
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!-- 引入上文配置的日志格式 -->
            <pattern>${pattern}</pattern>
        </encoder>
        <!--日志级别过滤器-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 配置过滤的日志级别 -->
            <level>ERROR</level>
            <!-- 大于配置的日志级别则放行 -->
            <onMatch>ACCEPT</onMatch>
            <!-- 小于配置的日志级别则放行 -->
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <!--日志拆分和归档压缩的 appender 对象-->
    <appender name="rollFile" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--日志文件保存路径-->
        <!--<file>${log_dir}/roll_logback.log</file>-->
        <!--日志消息格式配置-->
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${pattern}</pattern>
        </encoder>
        <!--指定拆分规则-->
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <!--按照时间和压缩格式声明拆分的文件名-->
            <!-- %d{yyyy-MM-dd}表示按日拆分,-号分割 ；
                log%i 表示拆分的文件 .log1.gz   .log2.gz
                   .gz .zip表示压缩包-->
            <fileNamePattern>${log_dir}/rolling.%d{yyyy-MM-dd-hh-mm-ss}.log%i.zip</fileNamePattern>
            <!--按照文件大小拆分-->
            <maxFileSize>1MB</maxFileSize>
        </rollingPolicy>
        <!--日志级别过滤器-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 配置过滤的日志级别 -->
            <level>ERROR</level>
            <!-- 大于配置的日志级别则放行 -->
            <onMatch>ACCEPT</onMatch>
            <!-- 小于配置的日志级别则放行 -->
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <!--异步日志-->
    <appender name="async" class="ch.qos.logback.classic.AsyncAppender">
        <!--指定某个具体的 appender-->
        <appender-ref ref="rollFile"/>
    </appender>

    <!--root logger 配置-->
    <!-- 打开所有的位置级别 -->
    <root level="ALL">
        <!-- 将上文定义的console控制台Appender添加进根目录 -->
        <appender-ref ref="console"/>
        <appender-ref ref="async"/>
    </root>
</configuration>
```

```tex
com.leex.logbackTest,helloLogback
[ERROR] 2021-03-08 22:06:47.578 com.leex.logbackTest helloLogback 19 [main] error

只有error日志别打印
```



官方提供的log4j.properties转换成logback.xml

https://logback.qos.ch/translator/



## logback-access配置

logback-access模块与Servlet容器（如Tomcat和Jetty）集成，以提供HTTP访问日志功能。我们可以使用logback-access模块来替换tomcat的访问日志。



1. 将logback-access.jar与logback-core.jar复制到$TOMCAT_HOME/lib/目录下

2. 修改$TOMCAT_HOME/conf/server.xml中的Host元素中添加：

```xml
<Valve className="ch.qos.logback.access.tomcat.LogbackValve" />
```

3. logback默认会在$TOMCAT_HOME/conf下查找文件 logback-access.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
	<!-- always a good activate OnConsoleStatusListener -->
	<statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener"/>
	<property name="LOG_DIR" value="${catalina.base}/logs"/>
	<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_DIR}/access.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        	<fileNamePattern>access.%d{yyyy-MM-dd}.log.zip</fileNamePattern>
        </rollingPolicy>
        <encoder>
            <!-- 访问日志的格式 -->
            <pattern>combined</pattern>
        </encoder>
    </appender>
    <appender-ref ref="FILE"/>
</configuration>
```

官方配置： https://logback.qos.ch/access.html#confifiguration