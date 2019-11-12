package com.wangkang;

import javax.annotation.PostConstruct;

/**
 * Description: 通过 import 导入
 *
 * @author wangkang
 * @date: 2019-07-24
 */

public class ImportNormal {

    private String name;
    private int age;

    public ImportNormal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public ImportNormal() {
        name = "kalg";
        age = 2;
    }

    @Override
    public String toString() {
        return "ImportNormal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 ImportNormal ....");
    }
}
