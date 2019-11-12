package com.wangkang.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:35 2018/12/25
 * @Modified By:
 */
@Data
@PropertySource("classpath:extend.yml")
@Configuration
public class ValueConfig {
    @Value("${myName}") // 注入配置文件【注意是$符号】
    private String name;

    @Value("${myAge}")
    private Integer age;

    @Value("注入普通字符串") // 注入普通字符串
    private String normal;

    @Value("#{systemProperties['os.name']}") // 注入操作系统属性
    private String osName;

    @Value("#{T(java.lang.Math).random() * 100.0 }") // 注入表达式结果，完全可以在配置文件中用表达式
    private double randomNumber;

    @Value("#{user.name}") // 注入其他Bean属性
    private String fromAnother;

    @Value("classpath:config/test.txt") // 注入文件资源
    private Resource testFile;

    @Value("https://www.baidu.com") // 注入网址资源
    private Resource testUrl;

    @Autowired // Properties可以从Environment获得
    private Environment environment;
}
