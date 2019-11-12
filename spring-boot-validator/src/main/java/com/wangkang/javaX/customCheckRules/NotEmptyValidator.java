package com.wangkang.javaX.customCheckRules;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:01 2018/12/24
 * @Modified By:
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty,String> {
    @Override
    public void initialize(NotEmpty constraintAnnotation) {
        //初始化得到注解数据
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(s)) {

            return false;
        }
        return true;
    }
}
