package com.wangkang.exception;

import com.wangkang.controller.AcmeController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:54 2018/12/25
 * @Modified By:
 */
@ControllerAdvice(basePackageClasses = AcmeController.class)
public class AcmeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(Throwable ex) {
        int code = ((MyException)ex).getCode();
        return new ResponseEntity<>(new MyException(code,((MyException)ex).getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
