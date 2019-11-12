## SPI
- Service Provider interface
- jdk6引入的一个新特性ServiceLoader，用来装载一系列的 service provider
- ServiceLoader读取 service provider 配置文件，装载指定的实现
- 服务提供者只需要在jar包中的META-INF/services/目录下创建文件（ServiceLoader规定了文件的路径PREFIX=META-INF/services/）
- 文件名为接口名，文件内容为接口的具体实现



## 应用
- DriverManager：获取Driver
- 插件：获取各个插件的实现

## 总结
- 用户可以自定义一些特定的功能，并注册到系统中








转自：https://zhuanlan.zhihu.com/p/28909673