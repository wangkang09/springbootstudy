package com.wangkang.doubleDefin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-07-25
 */
@Configuration
public class CCconfig {

    @Bean(name = "Cc")
    public CC cc() {
        return new CC();
    }

    @PostConstruct
    public void ff() {
        System.out.println("ccConfgi");
    }
}
