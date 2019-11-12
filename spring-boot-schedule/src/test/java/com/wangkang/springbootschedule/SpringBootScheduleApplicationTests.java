package com.wangkang.springbootschedule;

import com.wangkang.springbootschedule.quartz.HelloJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootScheduleApplicationTests {
    @Resource
    Scheduler scheduler;
    @Test
    public void contextLoads() {
        try {

            // define the job and tie it to our HelloJob class

            JobDetail job0 = newJob(HelloJob.class)
                    .withIdentity("myjob0", "mygroup0")
                    .usingJobData("wangkangJob0",true)
                    .build();

            Trigger trigger0 = newTrigger()
                    .withIdentity("mytrigger0", "mygroup0")
                    .startNow()
                    .usingJobData("wangkangTrigger0",true)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(4)
                            .repeatForever())
                    .build();

            JobDetail job1 = newJob(HelloJob.class)
                    .withIdentity("myjob1", "mygroup1")
                    .usingJobData("wangkangJob1",true)
                    .build();

            Trigger trigger1 = newTrigger()
                    .withIdentity("mytrigger1", "mygroup1")
                    .withSchedule(cronSchedule("0/10 * * * * ? *"))//cron表达式
                    .forJob(job1)
                    .build();


            scheduler.scheduleJob(job1,trigger0);
            //scheduler.checkExists(trigger0.getKey());//判断有没有
            scheduler.scheduleJob(trigger1);

            // and start it off
            //scheduler.start();

            // Tell quartz to schedule the job using our trigger
//          scheduler.scheduleJob(job1,trigger1);//step 1
//          scheduler.scheduleJob(job0,trigger0);//step 2
//          scheduler.scheduleJob(job1,trigger0);//step 3
//          在第3步的时候，因为trigger0.getkey!=job1.getkey而报错而且job1存在也会保存



//            scheduler.scheduleJob(job1,trigger1);
//            Set set = new HashSet();
//            set.add(trigger0);
//            scheduler.scheduleJob(job1,set,true);//这里将job1的jobkey复制给trigger0了。发生了insertTrigger和updateJobDetail
//
//            set = new HashSet();
//            set.add(trigger1);
//
//            scheduler.scheduleJob(job0,set,true);//这一步将job0的key复制给了tirgger0。发生了insertJobDetail和updateTrigger（关键：updateTrigger将qz_triggers更新了！）后记：因为trigger1之前已经有绑定啦，肯定要覆盖呀！
//            set.clear();
//            set.add(trigger1);//必须有绑定的jobDetail
//            scheduler.scheduleJob(job0,set,true);//这一步将job0的key复制给了tirgger1。发生了updateJobDetail和updateTrigger（关键：updateTrigger将qz_triggers更新了！）

            Thread.sleep(300000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

