package com.wangkang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:12 2018/12/26
 * @Modified By:
 */
//@WebListener
@Slf4j
public class MyAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        log.info("complete event");
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        log.info("timeout event");
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        log.info("error event");
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
        log.info("start event");
    }
}
