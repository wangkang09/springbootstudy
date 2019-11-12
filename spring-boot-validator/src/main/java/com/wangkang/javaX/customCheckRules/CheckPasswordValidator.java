package com.wangkang.javaX.customCheckRules;


import com.wangkang.vo.User;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, User> {

    @Override
    public void initialize(CheckPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if(user == null) {
            return true;
        }

        //没有填密码
        if(!StringUtils.hasText(user.getName())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("类级别name")
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }

        if(!StringUtils.hasText(user.getSex())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("类级别sex1")
                    .addPropertyNode("sex")
                    .addConstraintViolation();
            return false;
        }

        //两次密码不一样
        if (!user.getName().trim().equals(user.getSex().trim())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("sex和name一样")
                    .addPropertyNode("sexName")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
