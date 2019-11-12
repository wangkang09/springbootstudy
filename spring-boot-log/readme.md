## Spring 集成日志框架
- Slf4j：为抽象接口
- log4j：具体实现
- logback：具体实现

**冲突解决**
- spring-boot-starter包中的logger依赖中有logback依赖，会与log4j依赖产生冲突
- 需要排除starter包中的依赖

**开启注解**
- lombok依赖可以开启Slf4j注解

- log4j并没有默认配置文件。所以必须有log4j配置文件，不然没有输出

- logback有默认配置，且有颜色配置，是log4j的进阶版

- 测试类 logback默认配置是没有颜色的


https://www.cnblogs.com/zhangzhen894095789/p/6640808.html