package com.wangkang.controller;

import com.wangkang.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 18:03 2018/12/25
 * @Modified By:
 */
@RestController
public class UserController {
    @PostMapping("/user")
    @ResponseBody
    public User userConvert(@RequestBody User user) {
//        user.setAge(user.getAge()+1);
        user.setName(user.getName()+"pp");
        return user;
    }
}
