package com.wangkang.zhuye;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:59 2019/2/15
 * @Modified By:
 */
public abstract class AbstractMyService {
    protected String word;

    public AbstractMyService(String word) {

        this.word = word;

    }


    public AbstractMyService() {

        this ("Hello");
    }

    @Autowired
    protected MyServiceProperties properties;

    public abstract String hello();
}
