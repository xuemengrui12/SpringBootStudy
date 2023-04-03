package com.xmr.common;

import com.xmr.vo.ResultVO;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Created by xmr on 2020/3/25.
 */
//@ControllerAdvice
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Logger logger = LoggerFactory.getLogger(ResultResponseBodyAdvice.class);

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 此处获取到request 为特殊需要的时候处理使用
//		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        // 下面处理统一返回结果（统一code、msg、sign 加密等）
        if (selectedConverterType == MappingJackson2HttpMessageConverter.class
                && (selectedContentType.equals(MediaType.APPLICATION_JSON))) {
            if (body == null) {
                return ResultVO.NULL;
            } else if (body instanceof ResultVO) {
                return body;
            } else {
                // 异常
                if (returnType.getExecutable().getDeclaringClass().isAssignableFrom(BasicErrorController.class)) {
                    ResultVO vo = new ResultVO(ResultCode.EXCEPTION);
                    HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
                    if (req.getRequestURL().toString().contains("localhost")
                            || req.getRequestURL().toString().contains("127.0.0.1")) {
                        vo.setData(body);
                    }
                    return vo;
                } else {
                    return new ResultVO(body);
                }
            }
        }
        return body;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.hasMethodAnnotation(ResultVO_IGNORE.class);
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<Object> handleMethodVoArgumentNotValidException(BindException ex) {
        FieldError err = ex.getFieldError();
        // err.getField() 读取参数字段
        // err.getDefaultMessage() 读取验证注解中的message值
        String message = "参数{".concat(err.getField()).concat("}").concat(err.getDefaultMessage());
        logger.info("{} -> {}", err.getObjectName(), message);
        return new ResponseEntity<Object>(new ResultVO(ResultCode.PARAM_INVALID, message), HttpStatus.OK);
    }

    /**
     * Validator 参数校验异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
            // 读取参数字段，constraintViolation.getMessage() 读取验证注解中的message值
            String paramName = pathImpl.getLeafNode().getName();
            String message = "参数{".concat(paramName).concat("}").concat(constraintViolation.getMessage());
            logger.info("{} -> {} -> {}", constraintViolation.getRootBeanClass().getName(), pathImpl.toString(), message);
            return new ResponseEntity<Object>(new ResultVO(ResultCode.PARAM_INVALID, message), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(new ResultVO(ResultCode.PARAM_INVALID, ex.getMessage()), HttpStatus.OK);
    }

}