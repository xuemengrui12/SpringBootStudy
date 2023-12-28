package com.xmr.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Author: Administrator
 * @Date: 24/12/2023
 * @LastEditTime: 24/12/2023 上午 10:18
 * @LastEditors: Administrator
 * @Description:
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {

    int alwaysUpdateSomeColumnById(T entity);

    int insertBatchSomeColumn(List<T> entityList);

    List<T> queryAll();
}
