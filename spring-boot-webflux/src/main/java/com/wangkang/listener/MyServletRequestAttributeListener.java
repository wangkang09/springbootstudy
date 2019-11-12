package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:56 2018/12/26
 * @Modified By:
 */
@Slf4j
@WebListener
//Request中的属性的增删改
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {

    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log.info("add request atrribute");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        log.info("remove request atrribute");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        log.info("replace request atrribute");
    }
}
