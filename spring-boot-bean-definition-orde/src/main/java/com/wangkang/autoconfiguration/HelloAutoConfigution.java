package com.wangkang.autoconfiguration;

import com.wangkang.Xentity.User;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-24
 */
public class HelloAutoConfigution {

    //这里可以匹配
//    @Bean
//    @ConditionalOnMissingBean
//    public User user() {
//        return new User("wk",12);
//    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 HelloAutoConfigution ....");
    }
}
