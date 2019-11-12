package com.wangkang.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Description: 设置validator
 * @Author: wangkang
 * @Date: Created in 17:38 2018/12/18
 * @Modified By:
 */
@Configuration
public class ValidatorConfigration {

    @Bean
    //Validator有默认设置，failFast为true时，只要有错直接返回，不往下校验了
    public Validator validator() {

        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(false)
                .buildValidatorFactory();


        return validatorFactory.getValidator();
    }

    //用于跨参数，Service层校验，注册postProcessor！开启方法级别校验
    @Bean
    public MethodValidationPostProcessor methodProcessor() {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator());

        return methodValidationPostProcessor;
    }


}
