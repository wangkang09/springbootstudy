package com.wangkang.controller;

import com.wangkang.exception.BusinessException;
import com.wangkang.exception.MessageCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:48 2018/12/26
 * @Modified By:
 */
@RestController
public class ThrowExceptionController {

    @GetMapping("/exception")
    public String exception(@RequestParam("page_size") Integer pageSize) {

        if (pageSize < 1) {
            throw new BusinessException(MessageCode.PAGESIZE_NOT_VALID);
        }
        return "success";
    }
}
