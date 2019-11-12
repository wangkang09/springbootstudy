package com.wangkang.springbootschedule.springTask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 20:51 2019/1/2
 * @Modified By:
 */
@Service
@Slf4j
public class TaskService {
    int count = 0;
    //从0秒开始，每3秒执行一次
    @Scheduled(cron="0/3 * * * * ? ")
    public void runTask(){
        log.info("{}",++count);
    }
}
