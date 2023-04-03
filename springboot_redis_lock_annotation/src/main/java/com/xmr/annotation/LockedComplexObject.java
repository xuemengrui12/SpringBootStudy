package com.xmr.annotation;

import java.lang.annotation.*;

/**
 * Created by xmr on 2020/3/6.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {

    String name() default "";//含有成员变量的复杂对象中需要加锁的成员变量，如一个商品对象的商品ID

}

