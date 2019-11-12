package com.wangkang.springbootschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:06 2019/1/2
 * @Modified By:
 */
//@Configuration
//@EnableScheduling
public class SpringTaskConfig {
    //@Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        //线程池大小
        scheduler.setPoolSize(10);
        //线程名字前缀
        scheduler.setThreadNamePrefix("spring-task-thread");
        return scheduler;
    }

//    @Scheduled所支持的参数：
//        1. cron：cron表达式，指定任务在特定时间执行；
//        2. fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms；
//        3. fixedDelayString：与fixedDelay含义一样，只是参数类型变为String；
//        4. fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms；
//        5. fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String；
//        6. initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms；
//        7. initialDelayString：与initialDelay的含义一样，只是将参数类型变为String；
//        8. zone：时区，默认为当前时区，一般没有用到。


}
