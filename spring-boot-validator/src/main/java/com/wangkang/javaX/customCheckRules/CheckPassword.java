package com.wangkang.javaX.customCheckRules;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:44 2018/12/24
 * @Modified By:
 */
@Target({ TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
//指定验证器
@Constraint(validatedBy = CheckPasswordValidator.class) //这里可以指定多个验证器
@Documented
public @interface CheckPassword {
    //默认错误消息
    String message() default "两个字符串不一样";

    //分组
    Class<?>[] groups() default { };

    //负载
    Class<? extends Payload>[] payload() default { };
}
