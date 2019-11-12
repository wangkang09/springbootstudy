package com.wangkang.controller;

import com.wangkang.config.ValueConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:02 2018/12/25
 * @Modified By:
 */
@RestController
public class ConfigTest {
    @Resource
    ValueConfig valueConfig;

    @GetMapping("/config")
    public String config() {
        return valueConfig.getName().toString() + valueConfig.getAge().toString();
    }
}
