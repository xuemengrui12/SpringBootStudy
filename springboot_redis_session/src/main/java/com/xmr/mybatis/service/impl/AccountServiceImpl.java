package com.xmr.mybatis.service.impl;

import com.xmr.mybatis.dao.AccountMapper;
import com.xmr.mybatis.model.Account;
import com.xmr.mybatis.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Account)表服务实现类
 *
 * @author makejava
 * @since 2020-03-13 21:19:02
 */
@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccountByName(String name) {
        return accountMapper.getAccountByName(name);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Long id) {
        return this.accountMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Account> queryAllByLimit(int offset, int limit) {
        return this.accountMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param Account 实例对象
     * @return 实例对象
     */
    @Override
    public Account insert(Account Account) {
        this.accountMapper.insert(Account);
        return Account;
    }

    /**
     * 修改数据
     *
     * @param Account 实例对象
     * @return 实例对象
     */
    @Override
    public Account update(Account Account) {
        this.accountMapper.update(Account);
        return this.queryById(Account.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.accountMapper.deleteById(id) > 0;
    }
}