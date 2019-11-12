package com.wangkang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:06 2018/12/26
 * @Modified By:
 */
@RestController
public class MyController {
    @GetMapping("/myFilter")
    public String filter() {
        return "success";
    }
}
