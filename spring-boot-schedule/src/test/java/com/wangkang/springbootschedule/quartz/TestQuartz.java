package com.wangkang.springbootschedule.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.annotation.Resource;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 9:50 2019/1/3
 * @Modified By:
 */
public class TestQuartz {


    @Test
    public void test0() {
//        try {
//            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//            // and start it off
//            scheduler.start();
//            // define the job and tie it to our HelloJob class
//
//            JobDetail job0 = newJob(HelloJob.class)
//                    .withIdentity("job0", "group0")
//                    .usingJobData("wangkangJob0",true)
//                    .build();
//
//
//            Trigger trigger0 = newTrigger()
//                    .withIdentity("trigger0", "group0")
//                    .startNow()
//                    .usingJobData("wangkangTrigger0",true)
//                    .withSchedule(simpleSchedule()
//                            .withIntervalInSeconds(4)
//                            .repeatForever())
//                    .build();
//
////            trigger0 = newTrigger()
////                    .withIdentity("trigger3", "group1")
////                    .withSchedule(cronSchedule("0 42 10 * * ?"))//cron表达式
////                    .build();
//
//            // Tell quartz to schedule the job using our trigger
//            scheduler.scheduleJob(job0,trigger0);
//
//
//            Thread.sleep(300000);
//            scheduler.shutdown();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
