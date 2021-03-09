# log4j配置文件详解

​	Log4j是Apache下的一款开源的日志框架，通过在项目中使用 Log4J，我们可以控制日志信息输出到控制台、文件、甚至是数据库中。我们可以控制每一条日志的输出格式，通过定义日志的输出级别，可以更灵活的控制日志的输出过程。方便项目的调试。

官方网站： http://logging.apache.org/log4j/1.2/

```xml
<!--log4j-->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```



先放一个例子

```properties
log4j.rootLogger =ALL,systemOut,logFile,logDailyFile,logRollingFile,logMail,logDB

#输出到控制台
log4j.appender.systemOut = org.apache.log4j.ConsoleAppender
log4j.appender.systemOut.layout = org.apache.log4j.PatternLayout
log4j.appender.systemOut.layout.ConversionPattern = [%-5p][%-22d{yyyy/MM/dd HH:mm:ssS}][%l]%n%m%n
log4j.appender.systemOut.Threshold = DEBUG
log4j.appender.systemOut.ImmediateFlush = TRUE
log4j.appender.systemOut.Target = System.out

#输出到文件
log4j.appender.logFile = org.apache.log4j.FileAppender
log4j.appender.logFile.layout = org.apache.log4j.PatternLayout
log4j.appender.logFile.layout.ConversionPattern = [%-5p][%-22d{yyyy/MM/dd HH:mm:ssS}][%l]%n%m%n
log4j.appender.logFile.Threshold = DEBUG
log4j.appender.logFile.ImmediateFlush = TRUE
log4j.appender.logFile.Append = TRUE
log4j.appender.logFile.File = ../Struts2/WebRoot/log/File/log4j_Struts.log
log4j.appender.logFile.Encoding = UTF-8
```

```java
@Test
public void helloLog4j(){

    // 创建日志记录器对象
    Logger logger = Logger.getLogger(Log4jTest.class);

    // 日志级别
    logger.fatal("fatal"); // 严重错误，一般会造成系统崩溃并终止运行

    logger.error("error"); // 错误信息，不会影响系统运行
    logger.warn("warn");   // 警告信息，可能会发生问题
    logger.info("info");   // 运行信息，数据连接、网络连接、IO 操作等等
    logger.debug("debug"); // 调试信息，一般在开发中使用，记录程序变量参数传递信息等等

    logger.trace("trace"); // 追踪信息，记录程序所有的流程信息

}
```



## Log4j组件

Log4J 主要由 Loggers (日志记录器)、Appenders（输出端）和 Layout（日志格式化器）组成。

- Loggers 控制日志的输出级别与日志是否输出；

- Appenders 指定日志的输出方式（输出到控制台、文件等）；

- Layout 控制日志信息的输出格式。



### Logger 

​	日志记录器，负责收集处理日志记录，实例的命名就是类“XX”的full quailied name（类的全限定名），Logger的名字大小写敏感，其命名有继承机制：例如：name为org.apache.commons的logger会继承name为org.apache的logger。

Log4J中有一个特殊的logger叫做“root”，他是所有logger的根，也就意味着其他所有的logger都会直接或者间接地继承自root。root logger可以用Logger.getRootLogger()方法获取。

​	但是，自log4j 1.2版以来， Logger 类已经取代了 Category 类。对于熟悉早期版本的log4j的人来说，Logger 类可以被视为 Category 类的别名。



```properties
log4j.rootLogger = [level],appenderName1,appenderName2,...
```

- level日志级别level是日志记录的优先级，分为OFF,TRACE,DEBUG,INFO,WARN,ERROR,FATAL,ALL 定义在枚举类`org.apache.log4j.Level`中

  ```java
  [level]位置填写日志级别，比配置的日志级别高的 日志 将被输出
  
  // OFF Level是最高等级的，用于关闭所有日志记录。
  final static public Level OFF = new Level(OFF_INT, "OFF", 0);
  //FATAL 指出每个严重的错误事件将会导致应用程序的退出。
  final static public Level FATAL = new Level(FATAL_INT, "FATAL", 0);
  //ERROR 指出虽然发生错误事件，但仍然不影响系统的继续运行。
  final static public Level ERROR = new Level(ERROR_INT, "ERROR", 3);
  //WARN 警告，表明会出现潜在的错误情形。
  final static public Level WARN  = new Level(WARN_INT, "WARN",  4);
  //INFO 一般和在粗粒度级别上，强调应用程序的运行全程
  final static public Level INFO  = new Level(INFO_INT, "INFO",  6);
  //DEBUG 一般用于细粒度级别上，对调试应用程序非常有帮助。
  final static public Level DEBUG = new Level(DEBUG_INT, "DEBUG", 7);
  //TRACE 是程序追踪，可以用于输出程序运行中的变量，显示执行的流程
  public static final Level TRACE = new Level(TRACE_INT, "TRACE", 7);
  // ALL Level是最低等级的，用于打开所有日志记录。
  final static public Level ALL = new Level(ALL_INT, "ALL", 7);
  ```

  Log4j常用的四个日志级别，优先级从低到高分别是DEBUG,INFO,WARN,ERROR

  比如在这里定义INFO级别，则应用程序中所有DEBUG级别的日志信息将不会别记录

- appenderName ：指定生效的appender。appender定义见下文







### appender

appender表示日志输出策略，log4j提供了很多日志输出的策略，我们也可以根据自己的需求自定义appender



![image-20210227235938419](F:/gitlocalRepository/imageRepository/default/log4j/image-20210227235938419.png)



| 输出策略类型             | **作用**                                                     |
| ------------------------ | ------------------------------------------------------------ |
| ConsoleAppender          | 将日志输出到控制台                                           |
| FileAppender             | 将日志输出到文件中                                           |
| DailyRollingFileAppender | 扩展FileAppender，将日志输出到一个日志文件，并且按一定时间（默认按日）分割输出到一个新的文件 |
| RollingFileAppender      | 扩展FileAppender，将日志信息输出到一个日志文件，并且指定文件的尺寸，当文件大小达到指定尺寸时，会自动把文件改名，同时产生一个新的文件 |
| JDBCAppender             | 把日志信息保存到数据库中                                     |
| WriterAppender           | 根据用户的选择把日志事件写入到Writer或者OutputStream。       |
| SMTPAppender             | 当特定的日志事件发生时，一般是指发生错误或者重大错误时，发送一封邮件。 |
| SocketAppender           | 给远程日志服务器（通常是网络套接字节点）发送日志事件（LoggingEvent）对象。 |
| SocketHubAppender        | 给远程日志服务器群组（通常是网络套接字节点）发送日志事件（LoggingEvent）对象。 |
| SyslogAppender           | 给远程异步日志记录的后台精灵程序(daemon)发送消息。           |
| TelnetAppender           | 一个专用于向只读网络套接字发送消息的log4j appender。         |



#### ConsoleAppender输出到控制台

```properties
#输出到控制台
# 控制台输出Appender
log4j.appender.systemOut = org.apache.log4j.ConsoleAppender
# 使用自定义日志格式化器
log4j.appender.systemOut.layout = org.apache.log4j.PatternLayout
# 自定义日志格式
log4j.appender.systemOut.layout.ConversionPattern = [%-5p][%-22d{yyyy/MM/dd HH:mm:ssS}][%l]%n%m%n
# 指定该Appender只的日志级别，优先级大于全局日志级别
log4j.appender.systemOut.Threshold = DEBUG
# 默认值是true,所有的消息都会被立即输出 
log4j.appender.systemOut.ImmediateFlush = TRUE
# 默认值System.out,输出到控制台(err为红色,out为黑色)
log4j.appender.systemOut.Target = System.out
#指定日志编码 
log4j.appender.systemOut.encoding=UTF-8
```



#### FileAppender将日志输出到文件中  

```properties
# 日志文件输出的 appender 对象
log4j.appender.file = org.apache.log4j.FileAppender
# 指定消息格式 layout
log4j.appender.file.layout = org.apache.log4j.PatternLayout
# 指定消息格式的内容
log4j.appender.file.layout.conversionPattern = [%-10p]%r  %l %d{yyyy-MM-dd HH:mm:ss.SSS} %m%n
# 指定日志文件保存路径
log4j.appender.file.file = /logs/log4j.log
# 指定日志文件的字符集
log4j.appender.file.encoding = UTF-8
```



#### RollingFileAppender按照文件大小拆分

```properties
# 按照文件大小拆分的 appender 对象
# 日志文件输出的 appender 对象
log4j.appender.rollingFile = org.apache.log4j.RollingFileAppender
# 指定消息格式 layout
log4j.appender.rollingFile.layout = org.apache.log4j.PatternLayout
# 指定消息格式的内容
log4j.appender.rollingFile.layout.conversionPattern = [%-10p]%r  %l %d{yyyy-MM-dd HH:mm:ss.SSS} %m%n
# 指定日志文件保存路径
log4j.appender.rollingFile.file = /logs/log4j1.log
# 指定日志文件的字符集
log4j.appender.rollingFile.encoding = UTF-8
# 指定日志文件内容的大小
log4j.appender.rollingFile.maxFileSize = 1MB
# 指定日志文件的数量
log4j.appender.rollingFile.maxBackupIndex = 10
```



#### DailyRollingFileAppender按时间拆分文件

```properties
# 按照时间规则拆分的 appender 对象
log4j.appender.dailyFile = org.apache.log4j.DailyRollingFileAppender
# 指定消息格式 layout
log4j.appender.dailyFile.layout = org.apache.log4j.PatternLayout
# 指定消息格式的内容
log4j.appender.dailyFile.layout.conversionPattern = [%-10p]%r  %l %d{yyyy-MM-dd HH:mm:ss.SSS} %m%n
# 指定日志文件保存路径
log4j.appender.dailyFile.file = /logs/log4j2.log
# 指定日志文件的字符集
log4j.appender.dailyFile.encoding = UTF-8
# 指定日期拆分规则 只能根据-分割，不支持：（冒号），'.'yyyy-MM-dd-HH-mm-ss 每秒分隔，'.'yyyy-MM-dd-HH 按小时分隔
log4j.appender.dailyFile.datePattern = '.'yyyy-MM-dd-HH-mm-ss
```



#### JDBCAppender日志信息保存到数据库

```properties
#mysql
log4j.appender.logDB=org.apache.log4j.jdbc.JDBCAppender
log4j.appender.logDB.layout=org.apache.log4j.PatternLayout
log4j.appender.logDB.Driver=com.mysql.jdbc.Driver
log4j.appender.logDB.URL=jdbc:mysql://localhost:3306/test
log4j.appender.logDB.User=root
log4j.appender.logDB.Password=root
# 日志输入语句
log4j.appender.logDB.Sql=INSERT INTO log(project_name,create_date,level,category,file_name,thread_name,line,all_category,message) values('itcast','%d{yyyy-MM-dd HH:mm:ss}','%p','%c','%F','%t','%L','%l','%m')
```

```sql
CREATE TABLE `log` (
	`log_id` int(11) NOT NULL AUTO_INCREMENT,
	`project_name` varchar(255) DEFAULT NULL COMMENT '目项名',
	`create_date` varchar(255) DEFAULT NULL COMMENT '创建时间',
	`level` varchar(255) DEFAULT NULL COMMENT '优先级',
	`category` varchar(255) DEFAULT NULL COMMENT '所在类的全名',
	`file_name` varchar(255) DEFAULT NULL COMMENT '输出日志消息产生时所在的文件名称 ',
	`thread_name` varchar(255) DEFAULT NULL COMMENT '日志事件的线程名',
	`line` varchar(255) DEFAULT NULL COMMENT '号行',
	`all_category` varchar(255) DEFAULT NULL COMMENT '日志事件的发生位置',
	`message` varchar(4000) DEFAULT NULL COMMENT '输出代码中指定的消息',
	PRIMARY KEY (`log_id`)
);
```



#### 自定义Appender

格式：log4j.logger.日志层级 = [level], 配置定义的appenderName

例如下文 定义的` log4j.logger.com.leex.log.log4j.log1 = info,rollingFile`

`Logger logger = Logger.getLogger(Log4jTest.class); `中 `Log4jTest` 层级在 `com.leex.log.log4j.log1` 将会只用`rollingFile`输出

```properties
# 自定义Logger 

log4j.logger.com.leex.log.log4j.log1 = info,rollingFile
log4j.logger.com.leex.log.log4j.log2 = info,dailyFile

```





还有很多其他的Appender以后用到了在研究吧





https://blog.csdn.net/breezylee09/article/details/84179653



### Layout

上文的`Appender`配置介绍中，我们已经使用到了 `Layout`配置

```properties
# 指定消息格式 layout
log4j.appender.file.layout = org.apache.log4j.PatternLayout
# 指定消息格式的内容
log4j.appender.file.layout.conversionPattern = [%-10p]%r  %l %d{yyyy-MM-dd HH:mm:ss.SSS} %m%n
```

layout 定义了日志输出级别与输出端，在输出端中分别配置日志的输出格式



同样log4j也定义了很多Layout配置提供选择



![image-20210228000432885](F:/gitlocalRepository/imageRepository/default/log4j/image-20210228000432885.png)



| 消息格式类型  | **作用**                                                     |
| ------------- | ------------------------------------------------------------ |
| SimpleLayout  | 简单的日志输出格式化，打印的日志格式为（info - message）     |
| HTMLLayout    | 格式化日志输出为HTML表格形式                                 |
| PatternLayout | 最强大的格式化期，可以根据自定义格式输出日志，如果没有指定转换格式，就是用默认的转换格式 |

```properties
* log4j 采用类似 C 语言的 printf 函数的打印格式格式化日志信息，具体的占位符及其含义如下：
# %m   输出代码中指定的日志信息
# %p   输出优先级，及 DEBUG、INFO 等
# %n   换行符（Windows平台的换行符为 "\n"，Unix 平台为 "\n"）
# %r   输出自应用启动到输出该 log 信息耗费的毫秒数
# %c   输出打印语句所属的类的全名
# %t   输出产生该日志的线程全名
# %d   输出服务器当前时间，默认为 ISO8601，也可以指定格式，如：%d{yyyy年MM月dd日 HH:mm:ss}
# %l   输出日志时间发生的位置，包括类名、线程、及在代码中的行数。如：Test.main(Test.java:10)
# %F   输出日志消息产生时所在的文件名称
# %L   输出代码中的行号
# %%   输出一个 "%" 字符

* 可以在 % 与字符之间加上修饰符来控制最小宽度、最大宽度和文本的对其方式。如：
# %5c  	输出category名称，最小宽度是5，category<5，默认的情况下右对齐 
# %-5c 	输出category名称，最小宽度是5，category<5，"-"号指定左对齐,会有空格 
# %.5c 	输出category名称，最大宽度是5，category>5，就会将左边多出的字符截掉，<5不 会有空格 
# %20.30c category名称<20补空格，并且右对齐，>30字符，就从左边交远销出的字符截掉
```

