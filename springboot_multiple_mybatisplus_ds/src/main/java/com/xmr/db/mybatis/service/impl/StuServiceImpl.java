package com.xmr.db.mybatis.service.impl;


import com.xmr.db.mybatis.dao.ds2.StuMapper;
import com.xmr.db.mybatis.domain.Stu;
import com.xmr.db.mybatis.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xmr on 2018/3/15.
 */

@Service
public class StuServiceImpl implements IStuService{
    @Autowired
    private StuMapper stuMapper;

    @Override
    public void addStu(String name, String sex) {
        stuMapper.addStu(name,sex);
    }


    @Override
    public Stu getStu(Long id) {
        return stuMapper.getStu(id);
    }


    @Override
    public void updateStu(int id, String name) {
        stuMapper.updateStu(id,name);
    }


    @Override
    public void deleteStuById(Long id) {
        stuMapper.deleteStuById(id);
    }
}
