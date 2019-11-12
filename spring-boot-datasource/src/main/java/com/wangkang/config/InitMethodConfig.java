package com.wangkang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:35 2019/5/10
 * @Modified By:
 */
@Configuration
public class InitMethodConfig {
    @Bean(initMethod = "initMethod")
    TestInitMethod testInitMethod() {
        return new TestInitMethod();
    }
}
