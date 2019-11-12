package com.wangkang;

import com.wangkang.zhuye.AbstractMyService;
import com.wangkang.zhuye.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:04 2019/2/15
 * @Modified By:
 */
@Component
@Slf4j
public class Runner1 implements CommandLineRunner {

    @Autowired
    private MyService service;

    @Override
    public void run(String... args) {

        log.info(service.hello());
    }

}
