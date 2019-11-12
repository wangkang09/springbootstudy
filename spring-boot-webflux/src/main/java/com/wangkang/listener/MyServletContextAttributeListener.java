package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:10 2018/12/26
 * @Modified By:
 */
@Slf4j
@WebListener
//context属性的增删改
public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    public void attributeAdded(ServletContextAttributeEvent scae) {
        log.info("add context attribute");
    }

    public void attributeRemoved(ServletContextAttributeEvent scae) {
        log.info("remove context attribute");
    }

    public void attributeReplaced(ServletContextAttributeEvent scae) {
        log.info("replace context attribute");
    }
}
