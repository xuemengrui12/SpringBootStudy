package com.xmr.config;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Administrator
 * @Date: 23/12/2023
 * @LastEditTime: 23/12/2023 下午 11:38
 * @LastEditors: Administrator
 * @Description:
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {
    public MySqlInjector() {
        super();
    }
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methods = super.getMethodList(mapperClass,tableInfo);
        methods.add(new InsertBatchSomeColumn(i-> i.getFieldFill() != FieldFill.UPDATE));
        methods.add(new AlwaysUpdateSomeColumnById(i-> i.getFieldFill() != FieldFill.INSERT));
        methods.add(new QueryAllMethod());
        return methods;
    }
}
