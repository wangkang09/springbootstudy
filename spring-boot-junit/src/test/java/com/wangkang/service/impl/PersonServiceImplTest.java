package com.wangkang.service.impl;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @Description:
 * 1. @Test(timeout = 1000)：如果测试用例用时超过1s，则失败
 * 2. @Test(expected = NullPointerException.class)：如果测试用例没有报NPE错，则失败
 *
 */
public class PersonServiceImplTest {

    @Test
    public void selectByExample() {
    }

    @Test(timeout = 1000) //org.junit.runners.model.TestTimedOutException: test timed out after 1000 milliseconds
    public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Complete");
    }

    @Test(expected = NullPointerException.class)
    public void testNullException() {
        //throw new NullPointerException();
    }
}