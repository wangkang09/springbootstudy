package com.wangkang;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-24
 */
public class RegisterBean {

    private String name;
    private int age;

    public RegisterBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public RegisterBean() {
        name = "kalg";
        age = 2;
    }

    @Override
    public String toString() {
        return "RegisterBean{" +
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
        System.out.println("初始化 RegisterBean ....");
    }
}
