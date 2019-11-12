package com.wangkang.config;

import com.wangkang.controller.MyServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:19 2018/12/26
 * @Modified By:
 */
@Configuration
public class ServeltConfig {

    //可以直接通过@WebServlet注解启用
//    @Bean
//    public ServletRegistrationBean myServlet() {
//        return new ServletRegistrationBean(new MyServlet(),"/myServlet");
//    }
}
