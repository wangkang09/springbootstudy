### 项目的父模块是 pageinfo模块，依赖了其中的pom

### 注意
- 如果person不加hashcode/equals，内容相同的person当作key，不会覆盖
- 缓存注解返回的value是返回最后return的值


### 遗留问题
- 用mockMVC做单元测试时，没有启动filter，应该不是@ServletCompentScan的位置问题，回来看看mockMVC方法说明
- https://www.cnblogs.com/duanxz/p/3539198.html  
- mockMVC测试时，只启动了spring context，filter等其它组件需要自己注入！
- 可以通过TestRestTemplate来进行接口测试！

### cacheManage

Spring提供了CacheManager和Cache接口用来统一各种不同的数据缓存技术.
- CacheManager是各种缓存技术抽象接口.
- Cache接口包含各种缓存操作.
- 不管使用什么缓存技术,都需要注册一个该实现的CacheManager的Bean
-  Spring Boot自动配置了CacheManager的各种实现,默认情况下使用的是ConcurrentMapCacheManager.支持以spring.cache为前缀的属性来配置缓存相关的配置

### 事务和缓存
- @EnableTransactionManagement(order = LOWEST_PRECEDENCE - 1) //配置使得事务比缓存优先级大
- 当按以上配置时，如果同时在方法上加两个注解，每次都先去数据库取，缓存不起作用了

### Spring Cache

@Cacheable(values,key,condition)

- 标记在方法上时：表示该方法支持缓存
- 标记在类上时：表示该类中的所有方法都支持缓存
- 每次在执行方法前都先去缓存看，不存在再执行方法
- values：表示方法返回值会被放在哪几个缓存中
- key：表示返回值对应的key，#参数名/#p+参数位置（#p1/#p2.id）；也有默认策略
- condition：默认为true进行缓存，但设置为false时不缓存。例：condition="#id%2==0"，表达式为true时才缓存

@CachePut(values)

- 表示不会去缓存中取，执行完方法后，将结果放入缓存

@CacheEvict(values,key,condition,allEntries,beforeInvocation)

- 标记在类上时，所有方法都执行缓存清除操作
- allEntries：默认为false，当指定为true时，表示清除所有元素
- beforeInvocation：默认为false（方法发生异常未能执行成功则不会触发清除缓存），当指定为true时，表示在执行方法前先清除指定元素。

@Caching
- 在一个方法或类上，指定多个以上注解
```
    @Caching(cacheable = @Cacheable("users"), evict = { @CacheEvict("cache2"),
       @CacheEvict(value = "cache3", allEntries = true,condition="#id%2==0") },put = {@CachePut(value =	      {    {"cache4","cache5"})})   
    public User find(Integer id) {
        return null;
    }
    
```

自定义注解

    //相当于@Cacheable(value="users")注解
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Cacheable(value="users")
    public @interface MyCacheable {
    }

基于XML配置


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


---
[spring缓存配置](../notice/springCache&cacheManage.md)

[事务配置](../notice/SpringTransaction.md)

[缓存策略及问题](../notice/缓存策略.md)

Ordered.LOWEST_PRECEDENCE

https://sylvanassun.github.io/2016/08/01/2016-08-1-Spring-data-transaction/

https://www.cnblogs.com/fashflying/p/6908028.html