package com.xmr.common;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * Created by xmr on 2020/3/25.
 */
@ControllerAdvice
@Component
@Order(1)
public class ValidatorExceptionHandler {

    @ExceptionHandler(value = ValidationException.class)
    @ResponseBody
    public Object exceptionHandler(ValidationException e, HttpServletRequest request) {

        String msg = new String();
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                msg = item.getMessage();
            }
        }
        return ResponseEntity.badRequest().body(msg);

    }
}
