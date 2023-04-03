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
//@Scope("prototype")   //声明scope为prototype
public class StuServiceImpl implements IStuService {
    @Autowired
    private StuMapper stuMapper;
//    @Autowired
//    private SqlSessionTemplate ds1SqlSessionTemplate;

    @Override
    public void addStu(String name, String sex) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);
        stuMapper.addStu(name,sex);
    }


    @Override
    public Stu getStu(Long id) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);

        return stuMapper.getStu(id);
    }


    @Override
    public void updateStu(int id, String name) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);

        stuMapper.updateStu(id,name);
    }


    @Override
    public void deleteStuById(Long id) {
//        UserMapper userMapper=ds1SqlSessionTemplate.getMapper(UserMapper.class);

        stuMapper.deleteStuById(id);
    }
}
