package com.wangkang.controller;

import com.wangkang.entity.User;
import com.wangkang.exception.MyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:59 2018/12/25
 * @Modified By:
 */
@RestController
public class AcmeController {

    @ResponseBody
    @PostMapping("/exception0")
    public ResponseEntity<?> acme(@RequestBody User user) throws Exception {

        if (user.getName().equals("wk")) {
                throw new MyException(555,"wkwkwkwkwk");
        }
        return null;
    }
}
