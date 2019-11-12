package com.wangkang.simpleSchedule;

import com.wangkang.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:06 2018/12/25
 * @Modified By:
 */
@Slf4j
@Component
@Order(1)
public class Schedule2 implements CommandLineRunner {
    @Resource
    User user;

    @Override
    public void run(String... strings) throws Exception {
        int count = 10;
        int total = count;
        while (count>=0) {
            log.info("Schedule1第{}次执行,{}",total - --count,user.toString());
            Thread.sleep(1000);
        }
    }
}
