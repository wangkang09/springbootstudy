package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:43 2018/12/26
 * @Modified By:
 */
@Slf4j
@WebListener
//context的创建与销毁
//可以在次初始化和销毁一些资源（数据库连接池）
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("init servelt context");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("destroy servelt context");
    }
}
