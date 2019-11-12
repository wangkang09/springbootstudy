package com.wangkang.config;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:35 2019/5/10
 * @Modified By:
 */
public class TestInitMethod implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet()");
    }

    @PostConstruct
    public void init() {
        System.out.println("inti()");
    }

    public void initMethod() {
        System.out.println("initMethod()");
    }
}
