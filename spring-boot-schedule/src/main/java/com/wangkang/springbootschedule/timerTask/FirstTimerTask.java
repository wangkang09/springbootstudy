package com.wangkang.springbootschedule.timerTask;

import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:31 2018/12/26
 * @Modified By:
 */
@Slf4j
//使用这种方式可以让你的程序按照某一个频度执行，但不能在指定时间运行（能）
//每一个Timer仅对应唯一一个线程
//Timer不保证任务执行的十分精确
//Timer类的线程安全的
//count在递增
public class FirstTimerTask extends TimerTask {
    int count = 0;
    @Override
    public void run() {
        log.info("第 {} 次执行Task",++count);
    }
}
