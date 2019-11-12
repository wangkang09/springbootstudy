package com.wangkang.config;

import com.wangkang.endpoint.MyEndPoint;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-11-08
 */
@Configuration
public class EndpointConfig {
    @Configuration
    static class MyEndpointConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnAvailableEndpoint
        public MyEndPoint myEndPoint() {
            return new MyEndPoint();
        }
    }
}
