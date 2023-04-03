package com.xmr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by xmr on 2020/3/25.
 */
@Validated
@Service
public class ValidatorService {
    private static final Logger logger = LoggerFactory.getLogger(ValidatorService.class);

    public String show(@NotNull(message = "不能为空") @Min(value = 18, message = "最小18") String age) {
        logger.info("age = {}", age);
        return age;
    }

}
