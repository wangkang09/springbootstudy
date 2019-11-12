package com.wangkang.zhuye;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:00 2019/2/15
 * @Modified By:
 */
public class MyService extends AbstractMyService {

    public MyService(String word) {
        super(word);
    }

    public MyService(){}

    @Override
    public String hello() {

        return String.format("V1 %s >> %s:%s !!", word, properties.getName(), properties.getAge());
    }
}
