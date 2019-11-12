package com.wangkang.config;

import com.wangkang.entity.JedisConnectionFactory;
import com.wangkang.entity.JedisPoolConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:56 2018/12/25
 * @Modified By:
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConfigurationProperties(prefix="spring.redis.pool-config")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    @ConfigurationProperties(prefix="spring.redis")
    public JedisConnectionFactory getConnectionFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        //不需要setPoolConfig，直接就设置成功了
//        JedisPoolConfig config = getRedisConfig();
//        factory.setPoolConfig(config);
        return factory;
    }


}

