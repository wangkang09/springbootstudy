package com.wangkang.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:17 2018/12/26
 * @Modified By:
 */
@Service
public class UserService<T> {

    public Mono<T> getUser() {
        return null;
    }
}
