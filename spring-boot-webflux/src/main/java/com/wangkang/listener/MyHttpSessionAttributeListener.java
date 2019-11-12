package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:58 2018/12/26
 * @Modified By:
 */
@Slf4j
@WebListener
//session 增删改
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent se) {
        log.info("add session attribute");
    }

    public void attributeRemoved(HttpSessionBindingEvent se) {
        log.info("remove session attribute");
    }

    public void attributeReplaced(HttpSessionBindingEvent se) {
        log.info("replace session attribute");
    }
}
