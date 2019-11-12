package com.wangkang.springbootschedule.timerTask;

import org.junit.Test;

import java.util.Calendar;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:35 2018/12/26
 * @Modified By:
 */
public class TestTimerTaskTest {


    @Test
    public void test0() {
//        TestTimerTask testTimerTask = new TestTimerTask();
//        //即第一次执行是在当前时间的0秒之后，之后每隔a秒钟执行一次
//        testTimerTask.test(3);
        System.out.println(-Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
    }
    @Test
    public void test1() {
        TestTimerTask testTimerTask = new TestTimerTask();
        //在a秒后执行一次
        testTimerTask.test1(3);
    }
    @Test
    public void test2() {
        TestTimerTask testTimerTask = new TestTimerTask();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,12,26,22,29);
        //即第一次执行是在当前时间的0秒之后，之后每隔a秒钟执行一次
        testTimerTask.test2(calendar);
    }
    @Test
    public void test3() {
        TestTimerTask testTimerTask = new TestTimerTask();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018,12,26,22,13);
        //即第一次执行是在当前时间的0秒之后，之后每隔a秒钟执行一次
        testTimerTask.test3(calendar,3);
    }


}