package com.xmr.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 锁的注解
 * Created by xmr on 2020/3/6.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    //redis 锁key的前缀
    String lockedPrefix() default "";

    //轮询锁的时间
    long timeOut() default 2000;

    //key在redis里存在的时间，1000S
    int expireTime() default 1000;

    //超时时间单位
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String delimiter() default ":";
}