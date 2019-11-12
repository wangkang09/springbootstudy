package com.wangkang.springbootschedule.HashedWheelTimer;


import io.netty.util.Timeout;
import io.netty.util.TimerTask;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-23
 */
public class WheelTask implements TimerTask {

    @Override
    public void run(Timeout timeout) throws Exception {
        System.out.println("timeout");
        System.out.println(timeout.timer().toString());
    }
}
