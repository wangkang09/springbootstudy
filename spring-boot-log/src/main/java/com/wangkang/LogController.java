package com.wangkang;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:12 2018/12/17
 * @Modified By:
 */
@RestController
public class LogController {

    @Resource
    LogService logService;

    @GetMapping("/log")
    public String test() {
        logService.log1();
        logService.log2();;
        logService.log3();
        return "log";
    }
}
