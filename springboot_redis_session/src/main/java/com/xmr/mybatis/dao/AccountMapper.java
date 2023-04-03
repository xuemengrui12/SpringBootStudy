package com.xmr.mybatis.dao;

import com.xmr.mybatis.model.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Account)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-13 21:18:55
 */
public interface AccountMapper {

    Account getAccountByName(String name);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Account queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Account> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param Account 实例对象
     * @return 对象列表
     */
    List<Account> queryAll(Account Account);

    /**
     * 新增数据
     *
     * @param Account 实例对象
     * @return 影响行数
     */
    int insert(Account Account);

    /**
     * 修改数据
     *
     * @param Account 实例对象
     * @return 影响行数
     */
    int update(Account Account);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}