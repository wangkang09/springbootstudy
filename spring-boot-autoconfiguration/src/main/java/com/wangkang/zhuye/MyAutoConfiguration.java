package com.wangkang.zhuye;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:01 2019/2/15
 * @Modified By:
 */
@Configuration
@EnableConfigurationProperties(MyServiceProperties.class)
public class MyAutoConfiguration {

    //v1
//    @Bean
//    MyService getMyService(){
//
//        return new MyService();
//    }

    //不行，只能从EnableConfigurationProperties获得
//    @Value("#{spring101.name}")
//    private String name;

//v2
    @Bean
    @ConditionalOnMissingBean(MyService.class)
    @ConditionalOnProperty(prefix = "spring101", name = "version", havingValue = "v1", matchIfMissing = true)
    //说明：如果version的值是v1或没有定义version的话，匹配
    MyService getMyService(){
        return new MyService();
    }


    @Bean
    @ConditionalOnProperty(prefix = "spring101", name = "version", havingValue = "v2")
    @ConditionalOnMissingBean(MyServiceV2.class)
        //说明：如果version的值是v2，匹配
    MyServiceV2 getMyServiceV2(){

        return new MyServiceV2();
    }
}