package com.wangkang.springbootschedule.HashedWheelTimer;


import io.netty.util.HashedWheelTimer;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-23
 */
public class WheelTaskTest {

    @Test
    public void test() throws InterruptedException {
        HashedWheelTimer timer = new HashedWheelTimer(200, TimeUnit.MILLISECONDS);

        timer.newTimeout(new WheelTask(),10000,TimeUnit.MILLISECONDS);
        Thread.sleep(20000);
    }

}