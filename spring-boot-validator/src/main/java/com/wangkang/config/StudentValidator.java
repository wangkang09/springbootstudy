package com.wangkang.config;

import com.wangkang.vo.Student;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:46 2018/12/18
 * @Modified By:
 */
public class StudentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //ValidationUtils.rejectIfEmpty(errors,"name","01","name is empty");
        Student stu = (Student) target;
        if (null == stu.getAge() || 50 < stu.getAge()) {
            errors.rejectValue("age","自定义ageToLager","自定义age too large");
        }
    }
}
