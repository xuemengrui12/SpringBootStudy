package com.xmr.mybatis.service;


import com.xmr.mybatis.model.Account;

import java.util.List;


/**
 * (Account)表服务接口
 *
 * @author makejava
 * @since 2020-03-13 21:18:56
 */
public interface IAccountService {

    Account getAccountByName(String name);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Account queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Account> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param Account 实例对象
     * @return 实例对象
     */
    Account insert(Account Account);

    /**
     * 修改数据
     *
     * @param Account 实例对象
     * @return 实例对象
     */
    Account update(Account Account);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}