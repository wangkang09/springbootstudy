- 若是Spring AOP，会有AOP自调用问题，可以手动注入代理类来解决
- 也可以使用aspectJ，没有自调用问题
    - 解决方法应该是，在增强代理方法的时候，递归增强

### 使用aspectJ
- 添加spring-aspects依赖！
- mode = AdviceMode.ASPECTJ
- 关键：使用aspectJ后，在接口上注解，没有效果，只能在类上注解，springaop可以在接口上注解！


### 说明
- controller中，分别对7个事务类型做了测试
- 测试都和理论一致

proxyTargetClass = true：使用cglib，false：使用java JDK代理
```java
@SpringBootApplication
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ,proxyTargetClass = true)
public class SpringBootTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionApplication.class, args);
	}

}
```





















https://stackoverflow.com/questions/3423972/spring-transaction-method-call-by-the-method-within-the-same-class-does-not-wo
https://www.ibm.com/developerworks/cn/java/j-master-spring-transactional-use/index.html
https://docs.spring.io/spring/docs/4.3.13.RELEASE/spring-framework-reference/htmlsingle/#transaction