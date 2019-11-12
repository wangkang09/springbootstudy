package com.wangkang.controller;

import com.wangkang.entity.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:10 2018/12/26
 * @Modified By:
 */
@RestController
@RequestMapping("/users")
public class WebfluxController {

    @GetMapping("/{user}")
    public Mono<User> getUser(@PathVariable Long user) {
        return null;
    }


    @DeleteMapping("/{user}")
    public Mono<User> deleteUser(@PathVariable Long user) {
        // ...
        return null;
    }

}
