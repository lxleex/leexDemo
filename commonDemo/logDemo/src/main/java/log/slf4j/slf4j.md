# slf4j

​		简单日志门面(Simple Logging Facade For Java) SLF4J主要是为了给Java日志访问提供一套标准、规范的API框架，其主要意义在于提供接口，具体的实现可以交由其他日志框架，例如log4j和logback等。slf4j自己也提供了功能较为简单的实现，但是一般很少用到。对于一般的Java项目而言，日志框架会选择slf4j-api作为门面，配上具体的实现框架（log4j、logback等），中间使用桥接器完成桥接。

官方网站： https://www.slf4j.org/

```
* 1. 使用SLF4J框架，可以在部署时迁移到所需的日志记录框架。 
* 2. SLF4J提供了对所有流行的日志框架的绑定，例如log4j，JUL，Simple logging和NOP。因此可以 在部署时切换到任何这些流行的框架。 
* 3. 无论使用哪种绑定，SLF4J都支持参数化日志记录消息。由于SLF4J将应用程序和日志记录框架分离， 因此可以轻松编写独立于日志记录框架的应用程序。而无需担心用于编写应用程序的日志记录框架。 
* 4. SLF4J提供了一个简单的Java工具，称为迁移器。使用此工具，可以迁移现有项目，这些项目使用日志 框架(如Jakarta Commons Logging(JCL)或log4j或Java.util.logging(JUL))到SLF4J。
```



## SLF4J入门

引入依赖

```xml
<!-- slf4j 日志门面 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.26</version>
</dependency>
<!-- slf4j 内置的简单实现-->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>1.7.21</version>
</dependency>
```

```java
package com.leex;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author : 86167
 * @Description : Slf4jTest 2021/3/7 17:49 86167
 */
public class Slf4jTest {

    public static final Logger LOGGER =
            LoggerFactory.getLogger(Slf4jTest.class);

    @Test
    public void helloSlf4j() {
        LOGGER.error("error");
        LOGGER.warn("warn");
        LOGGER.info("info");
        LOGGER.debug("debug");
        LOGGER.trace("trace");
        // 默认输出了info级别以上的信息


        // 使用站位符输出信息
        String str = "我是字符串";
        Integer i = 12;
        // 使用{} 作为占位符输出变量信息
        LOGGER.info("输出一行日志，{}", str);
        // 多传一个变量会怎样？  直接别忽略
        LOGGER.info("多传一个变量会怎样？，{}", str, i);
        // 少传一个变量会怎样？  多余的占位符输出了null
        LOGGER.info("少传一个变量会怎样？，第一个：{}, 第二个；{}", str);


        // 将系统异常信息输出
        try {
            int j = i / 0;
        }catch (Exception e){
            LOGGER.error("出异常啦出异常啦", e);
        }
    }

}
```



 ## 绑定日志的实现（Binding）

SLF4J支持各种日志框架。SLF4J发行版附带了几个称为“SLF4J绑定”的jar文件，每个绑定对应一个受支持的框架。



要切换日志框架，只需替换类路径上的slf4j绑定。例如，要从java.util.logging切换到log4j，只需将slf4j-jdk14-1.7.27.jar替换为slf4j-log4j12-1.7.27.jar即可。

SLF4J不依赖于任何特殊的类装载。实际上，每个SLF4J绑定在编译时都是硬连线的， 以使用一个且只有一个特定的日志记录框架。例如，slf4j-log4j12-1.7.27.jar绑定在编译时绑定以使用log4j。在您的代码中，除了*slf4j-api-1.7.27.jar*之外，您只需将您选择的**一个且只有一个**绑定放到相应的类路径位置。不要在类路径上放置多个绑定。以下是一般概念的图解说明。

![Image](F:/gitlocalRepository/imageRepository/default/slf4j/Image.png)



使用slf4j的日志绑定流程:

1. 添加slf4j-api的依赖

2. 使用slf4j的API在项目中进行统一的日志记录

3. 绑定具体的日志实现框架
   1. 绑定已经实现了slf4j的日志框架,直接添加对应依赖
   2. 绑定没有实现slf4j的日志框架,先添加日志的适配器,再添加实现类的依赖

4. slf4j有且仅有一个日志实现框架的绑定（如果出现多个默认使用第一个依赖日志实现）



### slf4j内置simple实现

见SLF4J入门



### logBack

```xml
<!--logback 日志实现-->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```



### nop 日志开关

优先级低于日志实现。需要去掉日志实现的jar包引入

```xml
<!-- slf4j-nop -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-nop</artifactId>
    <version>1.7.30</version>
    <scope>test</scope>
</dependency>
```



### log4j需要 引入适配器

```xml
<!-- log4j--> 
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.27</version>
</dependency>
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```



### java自带的JUL引入适配器

```xml
<!--绑定 jul 日志实现，需要导入适配器 -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-jdk14</artifactId>
    <version>1.7.25</version>
</dependency>
```



## 桥接旧的日志框架（Bridging）

​		通常，您依赖的某些组件依赖于SLF4J以外的日志记录API。您也可以假设这些组件在不久的将来不会切换到SLF4J。为了解决这种情况，SLF4J附带了几个桥接模块，这些模块将对log4j，JCL和java.util.logging API的调用重定向，就好像它们是对SLF4J API一样。

桥接解决的是项目中日志的遗留问题，当系统中存在之前的日志API，可以通过桥接转换到slf4j的实现

1. 先去除之前老的日志框架的依赖

2. 添加SLF4J提供的桥接组件

3. 为项目添加SLF4J的具体实现

![img](F:/gitlocalRepository/imageRepository/default/slf4j/legacy.png)

迁移的方式：

​	如果我们要使用SLF4J的桥接器，替换原有的日志框架，那么我们需要做的第一件事情，就是删除掉原

有项目中的日志框架的依赖。然后替换成SLF4J提供的桥接器。

```xml
<!-- log4j -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>log4j-over-slf4j</artifactId>
    <version>1.7.27</version>
</dependency>
<!-- jul -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jul-to-slf4j</artifactId>
    <version>1.7.27</version>
</dependency>
<!-- jcl -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>jcl-over-slf4j</artifactId>
    <version>1.7.27</version>
</dependency>
```

注意问题：

1. jcl-over-slf4j.jar和 *slf4j-jcl.jar*不能同时部署。前一个jar文件将导致JCL将日志系统的选择委托给SLF4J，后一个jar文件将导致SLF4J将日志系统的选择委托给JCL，从而导致无限循环。 

2. log4j-over-slf4j.jar和slf4j-log4j12.jar不能同时出现

3. jul-to-slf4j.jar和slf4j-jdk14.jar不能同时出现

4. 所有的桥接都只对Logger日志记录器对象有效，如果程序中调用了内部的配置类或者是Appender,Filter等对象，将无法产生效果。

   

##  SLF4J原理解析

1. SLF4J通过LoggerFactory加载日志具体的实现对象。

2. LoggerFactory在初始化的过程中，会通过performInitialization()方法绑定具体的日志实现。

3. 在绑定具体实现的时候，通过类加载器，加载org/slf4j/impl/StaticLoggerBinder.class

4. 所以，只要是一个日志实现框架，在org.slf4j.impl包中提供一个自己的StaticLoggerBinder类，在其中提供具体日志实现的LoggerFactory就可以被SLF4J所加载