package com.wangkang.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:43 2019/2/14
 * @Modified By:
 */
@Configuration
public class DruidConfig {
//    @Value("${spring.datasource.druid.user}")
//    private String druidUser;
//
//    @Value("${spring.datasource.druid.password}")
//    private String druidPassword;

//    @Bean(destroyMethod = "close", initMethod = "init")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DruidDataSource druidDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        return druidDataSource;
//    }
}
