package com.xmr.aspect;

import java.lang.annotation.*;

/**
 * @author xmr
 * @version 1.0
 * @Description: 日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    String description();
}
