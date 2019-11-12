package com.wangkang.controller;

import com.wangkang.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:15 2018/12/26
 * @Modified By:
 */
@Configuration
public class RoutingConfiguration {

//    @Bean
//    public RouterFunction<ServerResponse> monoRouterFunction(UserService userService) {
//        return route(GET("/{user}").and(accept(MediaType.APPLICATION_JSON)),null);
//
//    }
}
