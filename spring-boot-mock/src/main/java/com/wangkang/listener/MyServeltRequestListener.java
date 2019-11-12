package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:51 2018/12/26
 * @Modified By:
 */
@WebListener
@Slf4j
//Request的创建与销毁
@Component
public class MyServeltRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("destroy request");
    }

    public void requestInitialized(ServletRequestEvent sre) {
        log.info("init request {}");
    }
}
