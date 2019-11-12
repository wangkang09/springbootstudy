### 集成步骤
- 添加spring cache依赖
- 添加 redis依赖
- 默认就集成了redis cache了，不需要配置属性，有默认属性
- 在入口(main)类上加@EnableTransactionManagement注解，开启事务(因为此项目用到了事务，如果不用事务可以不开启)
- 在入口类上加@EnableCaching主键，开启缓存

### 测试总结
- 默认情况下，redisCache是非事务型cache，cache不加入事务（insertPerson方法测试）
- 如果想用Jedis客户端，可以直接将Jedis的配置类，复制到项目中就行，先扫描项目下的

- 生成bean的别名，用@bean(name = "***")，说明
- 注入bean，用@Qualifier(value = "***")，指定

- 通过设置cacheManager的((AbstractTransactionSupportingCacheManager) cacheManager).setTransactionAware(true);
- 设置事务型cache!!

```java
@Component
public class MyCacheManager implements CommandLineRunner {
    @Autowired
    CacheManager cacheManager;

    @Override
    public void run(String... strings) throws Exception {

        //读取配置文件，如果值为true的话，设置事务cache
        if (cacheManager instanceof AbstractTransactionSupportingCacheManager) {
            Properties properties = ConfigUtil.getProperties("application.properties");
            String transaction = properties.getProperty("ehcache_transaction_aware");
            if ("true".equals(transaction)) {
                ((AbstractTransactionSupportingCacheManager) cacheManager).setTransactionAware(true);
            }
        }
    }
}
```



### 配置说明
- spring.cache.type：强制设定cache的类型，可以为none从而不是用cache

- package org.springframework.boot.autoconfigure.data.redis 下，有redis自动配置的一些属性说明

- [redis自动配置说明][redis自动配置说明.md]


### 遗留问题
- 当使用默认的@SpringBootApplication注解时，@SpringBootApplication中的
- @EnableAutoConfiguration注解，可以将springboot自动配置的类，注入到主上下文中
- 但是，配置类并没有注入到主上下文中，所以，主上下文不能注入配置类

- 原因：可能是@Configuration注解的类，在父类上下文中，不能获取子类上下文的内容

- 证明：在org.springframework.包中可以成功注入application包中的@Configuration修饰的类。说明子类上下文可以获取父类上下文的内容


- 当在JedisConnectionConfiguration类中注入JedisConfg报错，不知道什么原因，spring可以解决循环依赖问题呀！

- 非事务的cache，导致的数据不一致的情况
    - cache保存成功（方法内部方法）
    - transaction失败
    - 导致cache和数据库的值不一致