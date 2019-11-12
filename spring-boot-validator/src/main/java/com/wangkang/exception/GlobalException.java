package com.wangkang.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:25 2018/12/24
 * @Modified By:
 */
@ControllerAdvice
@Slf4j
public class GlobalException  {

    @ExceptionHandler(ConstraintViolationException.class)
    public void constrainE(ConstraintViolationException e) {
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            log.error(violation.getMessage());
        }
    }

}


