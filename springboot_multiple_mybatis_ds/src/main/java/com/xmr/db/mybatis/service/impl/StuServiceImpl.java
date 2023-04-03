package com.xmr.db.mybatis.service.impl;


import com.xmr.db.mybatis.domain.Stu;
import com.xmr.db.mybatis.mapper.ds2.StuMapper;
import com.xmr.db.mybatis.service.IStuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xmr on 2018/3/15.
 */

@Service
//@Scope("prototype")   //声明scope为prototype
public class StuServiceImpl implements IStuService {
    @Autowired
    private StuMapper stuMapper;
//    @Autowired
//    private SqlSessionTemplate ds1SqlSessionTemplate;

    @Override
    public void addStu(String name, String sex) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);
        stuMapper.addStu(name, sex);
    }


    @Override
    public Stu getStu(Long id) {
        return stuMapper.getStu(id);
    }


    @Override
    public void updateStu(int id, String name) {
        stuMapper.updateStu(id, name);
    }


    @Override
    public void deleteStuById(Long id) {
        stuMapper.deleteStuById(id);
    }
}
