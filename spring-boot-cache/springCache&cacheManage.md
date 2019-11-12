### Spring Cache

------

  Spring提供了CacheManager和Cache接口用来统一各种不同的数据缓存技术.

- CacheManager是各种缓存技术抽象接口.
- Cache接口包含各种缓存操作.

| CacheManager              | 描述                                       |
| ------------------------- | ------------------------------------------ |
| SimpleCacheManager        | 使用简单的Collection存储缓存,主要用于测试. |
| NoOpCacheManager          | 不会实际存储缓存.                          |
| EhCacheCacheManager       | 使用EhCache缓存技术.                       |
| ConcurrentMapCacheManager | 使用ConcurrentMap存储缓存.                 |
| GuavaCacheManager         | 使用Google Guava的GuavaCache缓存技术.      |
| HazelcastCacheManager     | 使用Hazelcast缓存技术.                     |
| JCacheCacheManager        | 支持JCache(JSR-107)规范的实现作为缓存技术. |
| RedisCacheManager         | 使用Redis作为缓存技术.                     |

**不管使用什么缓存技术,都需要注册一个该实现的CacheManager的Bean.**



| Annotation  | Description                                                  |
| ----------- | ------------------------------------------------------------ |
| @CachePut   | 不管什么情况,都会把方法的返回值存入缓存中.@CachePut的属性与@Cacheable保持一致. |
| @Cacheable  | Spring会先查看缓存中是否存有数据,如果有,则直接返回缓存数据,如果没有,则将调用方法的返回值存入缓存中. |
| @Caching    | 可以通过@Caching注解组合多个注解策略在一个方法上.            |
| @CacheEvict | 将一条或多条缓存数据从缓存中删除.                            |

**开启声明式缓存需要在配置类上使用注解@EnableCaching**



 Spring Boot自动配置了CacheManager的各种实现,默认情况下使用的是ConcurrentMapCacheManager.支持以`spring.cache`为前缀的属性来配置缓存相关的配置.

```
spring.cache.type= #缓存技术的类型,可选ehcache,guava,simple,none,generic,hazelcast,infinispan,jcache,redis.
spring.cache.cache-name=#程序启动时创建缓存名称
spring.cache.ehcache.config=#ehcache配置文件的地址.
spring.cache.hazelcast.config=#hazelcast配置文件的地址.
spring.cache.infinispan.config=#infinispan配置文件的地址.
spring.cache.jcache.config=#jcache配置文件的地址.
spring.cache.jcache.provider=#当多个jcache实现在类路径中的时候,指定jcache实现.
spring.cache.guava.spec=# guava specs
```

  **使用Spring Boot只需要导入相关缓存技术的依赖,并在配置类使用@EnableCaching注解开启缓存支持即可.**

https://sylvanassun.github.io/2016/08/01/2016-08-1-Spring-data-transaction/