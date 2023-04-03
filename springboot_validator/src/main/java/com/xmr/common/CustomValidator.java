package com.xmr.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by xmr on 2020/3/25.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME) // 注解将会由虚拟机保留,以便它可以在运行时通过反射读取.
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Constraint(validatedBy = CustomValidatorClass.class)
public @interface CustomValidator{
    // 如果有效值多个，使用','隔开
    String values();

    // 提示内容
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
