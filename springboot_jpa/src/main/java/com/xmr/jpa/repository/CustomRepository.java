package com.xmr.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * Created by xmr on 2018/8/31.
 * 自定义Repository接口
 *
 * 通过实现 pagingAndSortingRepository 接口,具备分页和排序能力
 */
@NoRepositoryBean //@NoRepositoryBean指明当前这个接口不是我们领城类的接口
public interface CustomRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    Page<T> findByAuto(T example, Pageable pageable);

}
