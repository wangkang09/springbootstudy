package com.wangkang.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:04 2018/12/25
 * @Modified By:
 */
@EnableConfigurationProperties(EnableConfig0.class)
@Configuration
@Slf4j
public class EnableConfig {

    @Resource
    EnableConfig0 enableConfig0;

    @Resource
    ConfigurationPropertiesConfig configurationPropertiesConfig;

    public void en() {
        log.info(enableConfig0.toString());
        log.info(configurationPropertiesConfig.toString());
    }

}
