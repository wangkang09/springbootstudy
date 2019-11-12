package com.wangkang;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;

/**
 * Description:通过 import 导入
 *
 * @author wangkang
 * @date: 2019-07-24
 */
public class ImportRegistrarMy implements ImportBeanDefinitionRegistrar {

    private String BEAN_NAME = "com.wangkang.RegisterBean";
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("进入了 ImportRegistrarMy");
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(RegisterBean.class);
        beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(BEAN_NAME, beanDefinition);

    }

    @PostConstruct
    public void init() {
        System.out.println("初始化 ImportRegistrarMy ....");
    }
}
