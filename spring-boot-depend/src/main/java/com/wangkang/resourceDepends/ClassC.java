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
public class ClassC {
    @Resource
    ClassA classA;

    private String classc;

    public String getClassc() {
        return classc;
    }
    public void setClassc(String classc) {
        this.classc = classc;
    }
}
