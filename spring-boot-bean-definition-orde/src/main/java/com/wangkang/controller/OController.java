package com.wangkang.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Description: HelloController 在 它之前就已经加入了 beanDefinitionNames 中了，所以可以匹配
 *
 * @author wangkang
 * @date: 2019-07-24
 */
@RestController
@ConditionalOnBean(value = HelloController.class)
public class OController {

    @GetMapping("/oo")
    public void test() {
        System.out.println("/oo");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 OController ....");
    }
}
