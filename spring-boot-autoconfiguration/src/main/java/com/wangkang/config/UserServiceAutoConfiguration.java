package com.wangkang.config;

import com.wangkang.service.EnableProperty;
import com.wangkang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:26 2019/2/15
 * @Modified By:
 */
@Configuration
@EnableConfigurationProperties(EnableProperty.class)
public class UserServiceAutoConfiguration {

    @Autowired
    EnableProperty enableProperty;
//    @Value("#{user.name1}")
//    private String name;

    //serviceNameCanBeConfigured()测试专用
    @Bean
    @ConditionalOnProperty(value = "SerName")
    public UserService userService() {
        UserService userService = new UserService();
        userService.setSerName(enableProperty.getSerName());
        return userService;
    }
}
