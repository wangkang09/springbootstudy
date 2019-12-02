package com.wangkang.controller;

import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpoint;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-11-12
 */
@Component
@ControllerEndpoint(id = "controller")
public class EndpointController {

//controllerEndpoint 不能有 operation，why?
//    @ReadOperation
//    public Map readMap() {
//        Map<String,String> map = new HashMap<>();
//        map.put("1","1");
//        return map;
//    }
}
