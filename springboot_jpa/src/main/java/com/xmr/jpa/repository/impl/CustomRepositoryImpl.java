package com.xmr.jpa.repository.impl;

import com.xmr.jpa.repository.CustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by xmr on 2018/8/31.
 * 继承 SimpleJpaRepository 类让我们可以使用其提供的方法(如 findAll )
 *
 */
public class CustomRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID > implements CustomRepository<T, ID > {
    private final EntityManager entityManager;// 让数据操作方法中可以使用 entityManager。

    /*customReposltoryImp1的构造函数, 需当前处理的领城类类型和 entityManager作为构造参教,
    在这里也给我们的 entityManager赋值了*/
    public CustomRepositoryImpl(Class<T> domainClass,  EntityManager entityManager) {
        super(domainClass, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<T> findByAuto(T example, Pageable pageable) {
        return null;
    }
}
