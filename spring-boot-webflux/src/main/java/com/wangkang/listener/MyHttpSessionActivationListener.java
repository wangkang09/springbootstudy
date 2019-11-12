package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:00 2018/12/26
 * @Modified By:
 */
@Slf4j
//@WebListener
//session 的活化(从硬盘到内存)和钝化(从内存到硬盘)
public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
    public void sessionWillPassivate(HttpSessionEvent se) {
        log.info("passivate session");
    }

    public void sessionDidActivate(HttpSessionEvent se) {
        log.info("activate session");
    }
}
