package com.wangkang.doubleDefinClassAndMethod;

import com.wangkang.importConfig.ZMethod;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-26
 */

public class DefinClass {
    @Bean
    @ConditionalOnBean
    public ZMethod zMethod() {
        return new ZMethod();
    }
}
