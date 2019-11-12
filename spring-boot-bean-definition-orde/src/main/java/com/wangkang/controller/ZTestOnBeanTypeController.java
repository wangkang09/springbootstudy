package com.wangkang.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-25
 */
@Controller
@ConditionalOnBean(OnBeanNameController.class)
public class ZTestOnBeanTypeController {
    @PostConstruct
    public void init() {
        System.out.println("初始化 ZTestOnBeanTypeController ....");
    }
}
