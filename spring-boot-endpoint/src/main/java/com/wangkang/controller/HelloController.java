package com.wangkang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-11-12
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello controller";
    }
}
