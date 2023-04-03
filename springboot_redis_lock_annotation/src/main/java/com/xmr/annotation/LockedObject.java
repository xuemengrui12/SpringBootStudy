package com.xmr.annotation;

import java.lang.annotation.*;

/**
 * 锁的参数
 * Created by xmr on 2020/3/6.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedObject {


}
