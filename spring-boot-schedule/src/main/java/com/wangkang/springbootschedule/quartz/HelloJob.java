package com.wangkang.springbootschedule.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 9:58 2019/1/3
 * @Modified By:
 */
@Slf4j
public class HelloJob implements Job {
    public HelloJob() {}
    int count = 0;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("hello {} {}",jobExecutionContext.getJobDetail().getKey(),jobExecutionContext.getTrigger().getKey());
    }
}
