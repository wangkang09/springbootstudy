# spring-boot-study

## spring-boot-banner

- 修改spring项目启动时的默认图案

- http://patorjk.com/software/taag：to get the banner text


## spring-boot-commandlinerunner

- 定义简单定时任务

## spring-boot-log

- 集成日志框架
- 如果要集成log4j+slf4j，需要去除spring-boot-starter中的starter-logging依赖，因为logging包中有logback会与log4j产生冲突
- log4j没有默认配置，logback有，需要自定义log4j配置

## spring-boot-lombok

- 

## 注意

- 当子项目无效时，在Maven Project中，点击加号，选中pom.xml添加即可
- 当我们要扩展框架代码时，只要在项目中创建相同的包路径，再在包路径中创建要改的类，把框架代码复制进去，再做修改，走框架时，就不会走框架自带的类而是走我修改后的类

## 父类代码
- 在父类代码中添加一些抽象类，子类中添加父类依赖（不是parent依赖而是dependency），可以继承

## 自定义Json转换

```java
@JsonComponent
public class Example {

	public static class Serializer extends JsonSerializer<SomeObject> {
		// ...
	}

	public static class Deserializer extends JsonDeserializer<SomeObject> {
		// ...
	}
}
```

## 自定义convert HTTP requests and responses
```java
@Configuration
public class MyConfiguration {

	@Bean
	public CodecCustomizer myCodecCustomizer() {
		return codecConfigurer -> {
			// ...
		};
	}

}
```

## Order优先级
- Order的值越大，优先级越低
- OrderedCharacterEncodingFilter = Ordered.HIGHEST_PRECEDENCE
- 如果自定义一个配置，Order应该大于HIGHEST_PRECEDENCE，不然可能导致乱码
- 自定义一个servlet过滤器，Order应该小于或等于REQUEST_WRAPPER_FILTER_MAX_ORDER，这样才能先过滤
```
int HIGHEST_PRECEDENCE = -2147483648;
int LOWEST_PRECEDENCE = 2147483647;
int REQUEST_WRAPPER_FILTER_MAX_ORDER = 0;
```
- @EnableTransactionManagement 默认级别 = LOWEST_PRECEDENCE
- @EnableCaching 默认级别 = LOWEST_PRECEDENCE
- 如果一个方法同时要缓存和事务，最好清楚哪个优先

## log配置
- 因为springboot默认继承了logback，所以如果要集成log4j要去除spring-boot-starter中的logging-start，如log模块

## AOP自调用问题
- 因为如果在a()内部调用b()方法，这个b()方法是实际的方法，而不是$b()代理方法
- 通过注入代理类，来实现回调代理方法