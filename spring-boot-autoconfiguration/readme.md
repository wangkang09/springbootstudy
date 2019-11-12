## 自定义autoconfiguration
- 通过自定义autoconfiguration功能的库，可以很方便的使springboot自动加载依赖库所提供的功能
- 一般通过@configuration注解得到autoconfiguration的功能，同时使用@condition相关注解，限制功能使用的范围
- 如spring-boot-autoconfigure和mybatis-spring-boot-autoconfigure包下的META-INF/spring.factories设置了springboot自动配置类的范围
- springboot自动检测发布包下META-INF/spring.factories的文件，该文件提供自动配置类路径

### autoconfigure 顺序
- 使用@AutoConfigureAfter、@AutoConfigureBefore来规定特定的顺序
- 使用@AutoConfigureOrder来规定一般顺序

### condition 使用
- Class Conditions
- Bean Conditions
- Property Conditions
- Resource Conditions
- Web Application Conditions
- SpEL Conditions

#### Class Conditions
- ConditionalOnClass ConditionalOnMissingClass
- 在classpath下有/没有相关的class
- 使用value使，即使没有class，也没关系（测试不通过？理解有问题）
- 如果使用这两个注解，得到自定义注解，必须使用name来关联class，而不能使用value
- ConditionalOnClass中的value只有接口时，条件也成立


### 测试结论
- ConditionalOnClass中的value只有接口时，条件也成立
- .withPropertyValues，通过另一个类，来获取值




部分代码转自：https://www.cnblogs.com/lovecindywang/p/9732215.html