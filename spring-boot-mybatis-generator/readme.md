## mybatis generator
- 在pom.xml中添加相关plugins和mysql依赖
- 配置generatorConfig.xml，注意targetProject路径
- 运行 mybatis-generator:generate

## mybatis
- 用@Mapper注解mapper接口，或使用 @MapperScan扫描mapper接口包
- 使用这个配置*.xml路径，如果路径和mapper接口不在一个包下的话（target中是在一起的，只要resourse和java下的包一样）
- mybatis.mapper-locations=classpath:com.wangkang.mapper/*.xml


## maven plugin 
- 使用maven plugin 必须首先引入 maven plugin 依赖




https://github.com/itfsw/mybatis-generator-plugin  generator 插件
https://mybatis.org/generator/whatsNew.html  generator 官网
https://blog.csdn.net/jpf254/article/details/79571396 mybatis 自动生成包 not found primary key 的解决方案


