#### 关键类

1. `RedisAutoConfiguration`：自动配置主类
2. `RedisProperties`：配置文件中，可配置属性类
3. `JedisConnectionConfiguration`：`Jedis`客户端配置类
4. `LettuceConnectionConfiguration`：`Lettuce`客户端配置类
5. `RedisCacheConfiguration`：`redis`缓存配置类




#### 自动配置

1. 添加`spring-boot-starter-data-redis` 依赖，默认自动配置Lettuce客户端
2. 自动配置的关键类是`RedisProperties`，通过这个类，我们得到如下信息
   1. 默认客户端主机：`localhost`
   2. 默认`port`：6379
   3. 默认`database`：0
   4. 另外可以配置的有
      1. url
      2. password
      3. ssl
      4. timeout
      5. Sentinel
      6. cluster
      7. lettuce的shutdownTimeout
      8. pool
   5. 配置如下：

```yml
spring:
  redis:
    host: localhost
    port: 6379
    url: wangkang
    password: 123456
    database: 1

    timeout: 1000
    ssl: true

    jedis:
      pool:
        min-idle: 1 # default 0
        max-wait: 1000 # default -1ms
        max-active: 10 # default 8
        max-idle: 2 # default 8

    lettuce:
      pool:
        min-idle: 1 # default 0
        max-wait: 1000 # default -1ms
        max-active: 10 # default 8
        max-idle: 2 # default 8
      shutdown-timeout: 200 # default 100ms

    # 分布式设置
    cluster:
      max-redirects:
      nodes:
    sentinel:
      master:
      nodes:

#what is Sentinel
#Monitoring. Sentinel constantly check if your master and slave instances are working as expected.
#Notification. Sentinel can notify the system administrator, or another computer program, via an API, that something is wrong with one of the monitored Redis instances.
#Automatic failover. If a master is not working as expected, Sentinel can start a failover process where a slave is promoted to master, the other additional slaves are reconfigured to use the new master, and the applications using the Redis server informed about the new address to use when connecting.

# 监控redis主从实例是否正常
# 如果redis主从实例异常，通知系统管理员
# 自动故障转移：主实例失败时，自动切换一个从实例为主实例
```




####  设置Jedis客户端

1. 在 `spring-boot-starter-data-redis` 依赖中，移除`Lettuce`依赖，并添加`Jedis`依赖——高版本默认有`Lettuce`依赖，没有`Jedis`依赖
2. 添加`Jedis`依赖，并将`JedisConnectionConfiguration`类，复制到项目中——这样`Spring`先初始化项目中的`Jedis`，就不会初始化`Lettuce`了（因为容器中已经有`RedisConnectionFactory.class`了）
3. 1,2两种方法都行



#### 设置自定义客户端

1. 关键是配置 RedisConnectionFactory

```java
// 其中CustomRedisConnectionFactory类是自定义的配置
@Configuration
public class CostomRedisConnectionFactory {
    @Bean("CustomRedisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        return new CustomRedisConnectionFactory();
    }
}
```



#### 设置多个`Redis`客户端

##### 同时设置`Jedis`和`Lettuce`客户端

1. 添加`Jedis`依赖

2. 将`JedisConnectionConfiguration`类复制到项目中

3. 现在容器中有两个 `RedisConnectionFactory`，因为自动配置在别的地方要注入 `RedisConnectionFactory`，所以会报错(has two been)，报错主要有两个地方

   ```java
   // redis cacheManager，要注入 RedisConnectionFactory
   // 可以自定义cachemanager，来避免，自动配置
   @Bean
   public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory, ResourceLoader resourceLoader) {
       RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(this.determineConfiguration(resourceLoader.getClassLoader()));
       List<String> cacheNames = this.cacheProperties.getCacheNames();
       if(!cacheNames.isEmpty()) {
           builder.initialCacheNames(new LinkedHashSet(cacheNames));
       }
   
       return (RedisCacheManager)this.customizerInvoker.customize(builder.build());
   }
   
   // redisTemplate，要注入RedisConnectionFactory
   // 可以自定义redisTemplate，来避免，自动配置
   @Bean
   @ConditionalOnMissingBean(
       name = {"redisTemplate"}
   )
   public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
       RedisTemplate<Object, Object> template = new RedisTemplate();
       template.setConnectionFactory(redisConnectionFactory);
       return template;
   }
   
   @Bean
   @ConditionalOnMissingBean
   public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
       StringRedisTemplate template = new StringRedisTemplate();
       template.setConnectionFactory(redisConnectionFactory);
       return template;
   }
   ```

4. 简单方法：用@Primary注解在其中一个上，这样自动配置设置的就是这个配置了

   ```java
   @Bean(name = "myJedisConnectFactory")
   @Primary
   @ConditionalOnMissingBean(RedisConnectionFactory.class)
   public JedisConnectionFactory redisConnectionFactory() throws UnknownHostException {
       return this.createJedisConnectionFactory();
   }
   ```



```java
@Configuration
@ConditionalOnClass({RedisOperations.class})
//@enable注解配合@ConfigurationProperties注解，注入一个配置过的RedisProperties类
@EnableConfigurationProperties({RedisProperties.class})
//导入redis客户端配置（因为Lettuce在前面，所以默认导入lettuce客户端）
//如果想用Jedis客户端，可以排除Lettuce依赖，或copy一份Jedis的配置到项目中来，自然就先初始化Jedis了
@Import({LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class})
public class RedisAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(
        name = {"redisTemplate"}
    )
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
    @Bean
    @ConditionalOnMissingBean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
//-------------------------

@ConfigurationProperties(
    prefix = "spring.redis"
)
public class RedisProperties {
    private int database = 0;
    private String url;
    private String host = "localhost";
    private String password;
    private int port = 6379;
    private boolean ssl;
    private Duration timeout;
    private RedisProperties.Sentinel sentinel;
    private RedisProperties.Cluster cluster;
    private final RedisProperties.Jedis jedis = new RedisProperties.Jedis();
    private final RedisProperties.Lettuce lettuce = new RedisProperties.Lettuce();
    public static class Lettuce {
        private Duration shutdownTimeout = Duration.ofMillis(100L);
        private RedisProperties.Pool pool;
    }
    public static class Jedis {
        private RedisProperties.Pool pool;
    }
    public static class Sentinel {
        private String master;
        private List<String> nodes;
    }
    public static class Cluster {
        private List<String> nodes;
        private Integer maxRedirects;
    }
    public static class Pool {
        private int maxIdle = 8;
        private int minIdle = 0;
        private int maxActive = 8;
        private Duration maxWait = Duration.ofMillis(-1L);
    }
}

@Configuration
@ConditionalOnClass({RedisClient.class})
class LettuceConnectionConfiguration extends RedisConnectionConfiguration {
    private final RedisProperties properties;
    private final ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers;
}
@Configuration
@ConditionalOnClass({GenericObjectPool.class, JedisConnection.class, Jedis.class})
class JedisConnectionConfiguration extends RedisConnectionConfiguration {
    private final RedisProperties properties;
    private final ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers;
}
```