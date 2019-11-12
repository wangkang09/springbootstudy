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
public class ClassB {
    @Resource
    ClassC classC;

    private String classB;

    public String getClassB() {
        return classB;
    }

    public void setClassB(String classB) {
        this.classB = classB;
    }
}
