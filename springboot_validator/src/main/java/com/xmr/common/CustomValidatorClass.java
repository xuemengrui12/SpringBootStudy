package com.xmr.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by xmr on 2020/3/25.
 */
public class CustomValidatorClass implements ConstraintValidator<CustomValidator, Object> {
    // 临时变量
    private String values;

    // 初始化values的值
    @Override
    public void initialize(CustomValidator customValidator) {
        // 将注解内配置的值赋值给临时变量
        this.values = customValidator.values();
    }
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 分割定义的有效值
        String[] value_array = values.split(",");
        boolean isFlag = false;
        // 遍历比对有效值
        for (int i = 0; i < value_array.length; i++) {
            // 存在一致跳出循环，赋值isFlag=true
            if (value_array[i].equals(value)) {
                isFlag = true;
                break;
            }
        }
        // 返回是否存在boolean
        return isFlag;
    }
}
