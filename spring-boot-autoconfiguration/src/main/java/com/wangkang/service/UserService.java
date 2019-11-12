package com.wangkang.service;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:27 2019/2/15
 * @Modified By:
 */
public class UserService {

    private String type;
    private String serName;

    public UserService() {
    }

    public UserService(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }
}
