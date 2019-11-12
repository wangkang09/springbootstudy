package com.wangkang.controller;

import com.wangkang.javaX.customCheckRules.CrossParameter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:19 2018/12/24
 * @Modified By:
 */
@RestController
@Validated
public class CrossParameterController {

    @GetMapping("/crossPara")
    @CrossParameter(message = "controller层测试")
    public String crossPara(@RequestParam("name") String name,@RequestParam("sex") String sex) throws ConstraintViolationException{
        return "success";
    }
}
