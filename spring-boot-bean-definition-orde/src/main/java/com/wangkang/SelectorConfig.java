package com.wangkang;

import com.wangkang.Xentity.SelectorEntity;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * Description: 通过 importSelector 导入
 *
 * @author wangkang
 * @date: 2019-07-24
 */
public class SelectorConfig {

    @Bean
    public SelectorEntity selectorEntity() {
        return new SelectorEntity("wkwk",55);
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 SelectorConfig ....");
    }
}
