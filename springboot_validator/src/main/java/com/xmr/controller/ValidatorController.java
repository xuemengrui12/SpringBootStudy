package com.xmr.controller;

import com.xmr.service.ValidatorService;
import com.xmr.vo.ValidatorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 注意:
 * 1) 如果想在参数中使用 @NotNull 这种注解校验，就必须在类上添加 @Validated；
 * 2) 如果方法中的参数是对象类型，则必须要在参数对象前面添加 @Validated
 * Created by xmr on 2020/3/25.
 */
@Validated
@RestController
@RequestMapping("/valid")
public class ValidatorController {

    @Autowired
    private ValidatorService validatorService;

    /**
     * 直接参数校验
     * 要特别提醒的是，验证框架里面大部分都不需要我们显示设置message，每个注解框架都给了一个默认提示语，大多数提示还都比较友好
     *
     * @param email
     * @return
     */
    @GetMapping("/test1")
    public String test1(  @NotNull(message = "不能为空") @Size(max = 18, min = 6, message = "长度需要在6-32之间") @Email String email) {

        return "OK";
    }

    /**
     * 实体类校验
     *
     * @param validatorVo
     * @return
     */
    @PostMapping("/test2")
    public String test2(@Validated ValidatorVo validatorVo , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer msg = new StringBuffer();
            // 获取错误字段集合
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (ObjectError error : bindingResult.getAllErrors()) {
                // 添加到错误消息集合内
                msg.append(error.getDefaultMessage() + " \n ");
            }
            return msg.toString();
        } else {
            return "Validator OK";
        }

    }

    /**
     * 内部Service校验
     *
     * @return
     */
    @GetMapping("/test3")
    public String test3() {
        return validatorService.show("16");
    }
}
