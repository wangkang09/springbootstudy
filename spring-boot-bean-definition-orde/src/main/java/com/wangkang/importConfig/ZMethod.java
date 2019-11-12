package com.wangkang.importConfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Description: 配合 DefinClass 做测试，特意放到后面，使得 ImportConfig 先被扫描到，这样就可以让 Definclass 先被 loadBeanDefinition。最终并没有测试出什么有用的可以总结的信息出来。。
 *
 * @author wangkang
 * @date: 2019-07-26
 */
@Configuration
@ConditionalOnMissingBean(ZMethod.class)//一般就不应该这样做的哦，只有在方法上这样做才合理
public class ZMethod {
    @PostConstruct
    public void mm() {
        System.out.println("ZMethod");
    }
}
