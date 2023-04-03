package com.xmr.db.mybatis.dao.ds2;


import com.xmr.db.mybatis.domain.Stu;

/**
 * Created by xmr on 2019/7/15.
 */
public interface StuMapper {
    void addStu(String name, String sex);

    Stu getStu(Long id);

    void updateStu(int id, String name);

    void deleteStuById(Long id);
}
