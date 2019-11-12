package com.wangkang.javaX;

import com.wangkang.javaX.customCheckRules.CrossParameter;
import com.wangkang.javaX.group.Social;
import com.wangkang.javaX.group.student;
import lombok.Data;
import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:13 2018/12/19
 * @Modified By:
 */
public class ValidatorUtil {

    private static Validator validator = Validation.byProvider(HibernateValidator.class)
                                                   .configure()
                                                   .failFast(false)
                                                   .buildValidatorFactory().getValidator();


    public static <T> ValidationResult ValidateEntity(T obj) {

        Set<ConstraintViolation<T>> set = validator.validate(obj);
        //Set<ConstraintViolation<T>> set = validator.validate(obj, student.class);
        return generateErrorMsg(set);
    }

    public static <T> ValidationResult ValidateProperty(T obj, String propertyName) {
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        return generateErrorMsg(set);
    }

    private static <T> ValidationResult generateErrorMsg(Set<ConstraintViolation<T>> set) {
        ValidationResult result = new ValidationResult();
        if (!CollectionUtils.isEmpty(set)) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>(set.size());
            for (ConstraintViolation<T> v : set) {
                errorMsg.put(v.getPropertyPath().toString(), v.getMessage());
            }
            result.setErrorMsgs(errorMsg);
        }
        return result;
    }

    @Data
    public static class ValidationResult {
        private boolean hasErrors;
        private Map<String, String> errorMsgs;
    }


}


