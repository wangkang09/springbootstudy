package com.wangkang.classConditions;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.plugin.javascript.navig.Array;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:48 2019/2/15
 * @Modified By:
 */
@Configuration
@ConditionalOnClass(name = "mymy")
public class NoClassValueCondition {

    public NoClassValueCondition() {
        System.out.println("进入NoClassValueCondition");
    }

    @Bean
    @ConditionalOnClass(value = You.class)
    public My my() {
        System.out.println("进入my");
        return new My();
    }
}
