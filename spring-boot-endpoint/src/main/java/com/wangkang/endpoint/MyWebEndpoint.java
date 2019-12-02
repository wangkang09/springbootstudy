package com.wangkang.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-11-14
 */
@WebEndpoint(id = "webEndpoint")
@Component
public class MyWebEndpoint {

    @ReadOperation
    public Map<String, String> hello() {
        Map<String, String> result = new HashMap<>();
        result.put("my", "webEndpoint");
        return result;
    }
}
