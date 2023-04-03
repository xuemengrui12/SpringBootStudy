package com.xmr.db.mybatis.service.impl;

import com.xmr.db.mybatis.dao.ds1.UserMapper;
import com.xmr.db.mybatis.dao.ds2.StuMapper;
import com.xmr.db.mybatis.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by xmr on 2020/1/16.
 */
@Service
public class TestServiceImpl implements ITestService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StuMapper stuMapper;

    @Transactional
    @Override
    public void add(String name, String sex) {
        userMapper.addUser(name, sex);
        stuMapper.addStu(name, sex);
        int i = 1 / 0;
    }
}
