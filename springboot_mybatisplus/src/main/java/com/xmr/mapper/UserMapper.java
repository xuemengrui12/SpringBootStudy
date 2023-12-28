package com.xmr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmr.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: Administrator
 * @Date: 2022/6/1
 * @LastEditTime: 2022/6/1 23:16
 * @LastEditors: Administrator
 * @Description:
 */
@Mapper
public interface UserMapper extends MyBaseMapper<User> {

}
