package com.wangkang.Xentity;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-24
 */
public class ImportConfigEntity {
    String name;
    int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ImportConfigEntity(String name, int age) {

        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "ImportConfigEntity{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 ImportConfigEntity ....");
    }
}
