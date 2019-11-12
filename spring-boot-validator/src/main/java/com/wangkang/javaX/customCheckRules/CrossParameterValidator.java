package com.wangkang.javaX.customCheckRules;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:15 2018/12/24
 * @Modified By:
 */
//这个注解要加，不然报错
@SupportedValidationTarget(value = { ValidationTarget.PARAMETERS })
public class CrossParameterValidator implements ConstraintValidator<CrossParameter, Object[]> {
    @Override
    public void initialize(CrossParameter constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if(value == null || value.length != 2) {
            throw new IllegalArgumentException("must have two args");
        }
        if(value[0] == null || value[1] == null) {
            return true;
        }
        if(value[0].equals(value[1])) {
            return true;
        }
        return false;
    }
}
