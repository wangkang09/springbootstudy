package com.wangkang.resourceDepends;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 22:53 2019/5/10
 * @Modified By:
 */
@Component
public class ClassA {
    @Resource
    ClassB classB;

    private String classA;

    public String getClassA() {
        return classA;
    }

    public void setClassA(String classA) {
        this.classA = classA;
    }
}
