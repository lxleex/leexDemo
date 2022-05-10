# JCL

全称为Jakarta Commons Logging，是Apache提供的一个通用日志API。

现在已经被SLF4J代替，简单了解一下吧

它是为 "所有的Java日志实现"提供一个统一的接口，它自身也提供一个日志的实现，但是功能非常常弱（SimpleLog）。所以一般不会单独使用它。他允许开发人员使用不同的具体日志实现工具: Log4j, Jdk自带的日志（JUL)





![image-20210304225348594](F:/gitlocalRepository/imageRepository/default/JCL/image-20210304225348594.png)

**我们为什么要使用日志门面：**

1. 面向接口开发，不再依赖具体的实现类。减少代码的耦合

2. 项目通过导入不同的日志实现类，可以灵活的切换日志框架

3. 统一API，方便开发者学习和使用
4. 统一配置便于项目日志的管理



添加依赖

```xml
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>
```

```java
/**
* 如果程序中导入了 log4j  JCL将自动使用  log4j的配置
* 如果没有发现配置，会使用JUL
*/
@Test
public void helloJCL(){

    // 获取 log日志记录器对象
    Log log = LogFactory.getLog(JCLTest.class);
    // 日志记录输出
    log.info("hello jcl");

}
```



## JCL原理

1. 通过LogFactory动态加载Log实现类

   ![image-20210304231059833](F:/gitlocalRepository/imageRepository/default/JCL/image-20210304231059833.png)

2. 日志门面支持的日志实现数组

   ```java
   private static final String[] classesToDiscover = 
       new String[]{"org.apache.commons.logging.impl.Log4JLogger",
                    "org.apache.commons.logging.impl.Jdk14Logger",
                    "org.apache.commons.logging.impl.Jdk13LumberjackLogger",
                    "org.apache.commons.logging.impl.SimpleLog"};
   ```

   按这个顺序加载，前面的没有继续加载后面的

3. 获取具体的日志实现

```java
for(int i = 0; i < classesToDiscover.length && result == null; ++i) {
    result = this.createLogFromClass(classesToDiscover[i], logCategory,
    true);
}
```

