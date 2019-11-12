package com.wangkang.controller;

import com.wangkang.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:16 2018/12/24
 * @Modified By:
 */
@RestController
@Slf4j
public class JavaxController {

    @PostMapping("/validator1")
    public String validator1(@RequestBody @Valid User user, BindingResult result) {
        List<String> re = new ArrayList<>();

        for (ObjectError objectError : result.getAllErrors()) {
            re.add(objectError.getObjectName() + "：" + objectError.getDefaultMessage());
        }
        log.info(re.toString());
        return re.toString();
    }

}
