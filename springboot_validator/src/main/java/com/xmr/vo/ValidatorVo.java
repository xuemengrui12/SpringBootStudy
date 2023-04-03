package com.xmr.vo;

import com.xmr.common.CustomValidator;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by xmr on 2020/3/25.
 */
@Data
public class ValidatorVo {

    @NotNull(message = "不能为空")
    @Size(max = 16, min = 6, message = "字符串长度需要在6-16之间")
    private String name;
    @Max(value = 100, message = "最大100")
    @Min(value = 18, message = "最小18")
    private String age;
    @CustomValidator(values = "0,1",message = "性别类型为0或1")
    private int sex;
}

