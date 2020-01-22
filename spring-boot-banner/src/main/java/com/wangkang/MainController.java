package com.wangkang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:38 2018/12/17
 * @Modified By:
 */
@RestController
public class MainController {

    @GetMapping("/banner")
    public String test(User user,String dd) {
        return "hello world";
    }

    @PostMapping("/banner")
    public String testPost(@RequestBody  User user) {
        return "hello world";
    }
}
