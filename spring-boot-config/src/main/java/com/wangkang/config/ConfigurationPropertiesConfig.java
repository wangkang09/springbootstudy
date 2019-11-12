package com.wangkang.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:58 2018/12/25
 * @Modified By:
 */
@ConfigurationProperties(prefix = "wang")
@PropertySource("classpath:configuration.properties")
@Configuration
@Data
public class ConfigurationPropertiesConfig {
    private String name;
    private String sex;
    private Integer age;
}
