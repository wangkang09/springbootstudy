package com.wangkang;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:16 2019/2/15
 * @Modified By:
 */
public class CGLibProxy implements Proxy {
    @Override
    public String proxyType(String type) {
        return type + "：cglibProxy!";
    }
}
