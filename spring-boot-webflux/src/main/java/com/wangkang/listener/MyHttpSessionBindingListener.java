package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:05 2018/12/26
 * @Modified By:
 */
@Slf4j
//@WebListener
/*
 *  unbound条件：
    a. 执行session.setAttribute("uocl", 非uocl对象) 时。
    b. 执行session.removeAttribute("uocl") 时。
    c. 执行session.invalidate()时。
    d. session超时后。

    bound条件：
    第一次session.setAttribute("my",my对象)
 *
 */
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {
    public void valueBound(HttpSessionBindingEvent event) {
        log.info(" bound session");
    }

    public void valueUnbound(HttpSessionBindingEvent event) {
        log.info(" unbound session");
    }
}
