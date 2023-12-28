package com.xmr.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Administrator
 * @Date: 2022/6/1
 * @LastEditTime: 2022/6/1 23:16
 * @LastEditors: Administrator
 * @Description:
 */
@Data
@TableName("`user`")
public class User {
    @TableId
    private Long id;
    private String name;
    private Integer age;
    private String email;
}