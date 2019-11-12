package com.wangkang.springbootschedule.timerTask;

import java.util.Calendar;
import java.util.Timer;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:24 2018/12/26
 * @Modified By:
 */
//schedule VS. scheduleAtFixedRate
//schedule：保证一个任务和下一个任务的间隔，下个任务开始的时间不固定
//scheduleAtFixedRate：下个任务开始时间固定的时间
public class TestTimerTask {
    boolean flag = false;
    private Timer timer = new Timer();
    public void test(int a) {
        //即第一次执行是在当前时间的0秒之后，之后每隔a秒钟执行一次
        timer.schedule(new FirstTimerTask(),0,1000*a);
        try {
            Thread.sleep(1000*(a+100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1(int a) {
        //在a秒后执行一次
        timer.schedule(new FirstTimerTask(),1000*a);
        try {
            Thread.sleep(1000*(a+100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2(Calendar  a) {
        //在calendar.getTime()时执行一次
        timer.schedule(new FirstTimerTask(),a.getTime());
        try {
            Thread.sleep(1000*100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void test3(Calendar c,int a) {
        //即第一次执行是在calendar.getTime()时执行,之后每隔a秒执行一次
        timer.schedule(new FirstTimerTask(),c.getTime(),1000*a);
        try {
            Thread.sleep(1000*(a+100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
