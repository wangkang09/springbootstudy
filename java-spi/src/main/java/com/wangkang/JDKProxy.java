package com.wangkang;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:15 2019/2/15
 * @Modified By:
 */
public class JDKProxy implements Proxy {
    @Override
    public String proxyType(String type) {
        return type + "：jdkProxy!";
    }
}
