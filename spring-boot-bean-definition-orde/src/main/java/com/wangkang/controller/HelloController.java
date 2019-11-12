package com.wangkang.controller;

import com.wangkang.Xentity.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-24
 */
@RestController
@ConditionalOnClass(OController.class)
public class HelloController {

    @GetMapping("/hello")
    public void test() {
        System.out.println("/hello");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 HelloController ....");
    }

    //这里并没有匹配到，因为到这里 beanDefinitionNames 中并没有 user 类型
    @Bean
    //@ConditionalOnBean
    public User user() {
        return new User("d",33);
    }
}
