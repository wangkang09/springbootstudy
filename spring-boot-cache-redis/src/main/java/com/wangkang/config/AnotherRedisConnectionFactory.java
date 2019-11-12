package com.wangkang.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: 向容器注入另一个RedisConnectionFactory
 * 容器初始化这个类后，就会调用post方法
 * @Author: wangkang
 * @Date: Created in 9:50 2019/1/18
 * @Modified By:
 */
@Component
public class AnotherRedisConnectionFactory implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        BeanDefinition redisConnectionFactory = beanDefinitionRegistry.getBeanDefinition("redisConnectionFactory");
//
//        if (redisConnectionFactory != null) {
//            BeanDefinition anotherRedisConnectionFactory = new RootBeanDefinition(JedisConnectionFactory.class);
//            beanDefinitionRegistry.registerBeanDefinition("myRedisConnectFactory",anotherRedisConnectionFactory);
//        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    //@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }
}
