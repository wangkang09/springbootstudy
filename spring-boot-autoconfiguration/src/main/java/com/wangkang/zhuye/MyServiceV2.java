package com.wangkang.zhuye;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:09 2019/2/15
 * @Modified By:
 */
@Service
public class MyServiceV2 extends AbstractMyService {

    public MyServiceV2(String word) {
        super(word);
    }

    public MyServiceV2(){}

    @Override
    public String hello() {
        return String.format("V2 %s >> %s:%s !!", word, properties.getName(), properties.getAge());
    }
}
