package com.wangkang.controller;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-11-12
 */
@Component
@ControllerEndpoint(id = "controller")
public class EndpointController {

//    @ReadOperation(produces="cend")
//    public String read() {
//        return "controller endpoint";
//    }

    @ReadOperation
    public Map readMap() {
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        return map;
    }
}
