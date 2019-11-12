package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:09 2018/12/26
 * @Modified By:
 */
@WebListener
@Slf4j
//Session的创建与销毁
public class MyHttpSessionListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("create {} session event",httpSessionEvent.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("destroy {} session event",httpSessionEvent.getSession());
    }

}
