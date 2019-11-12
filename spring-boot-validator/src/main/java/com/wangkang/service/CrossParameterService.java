package com.wangkang.service;

import com.wangkang.javaX.customCheckRules.CrossParameter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:27 2018/12/24
 * @Modified By:
 */
@Service
@Validated
public class CrossParameterService {

    @CrossParameter(message = "service测试")
    public void CrossParameter(String first, String second) {
    }
}
