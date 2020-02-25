package com.xmr.jpa.service;


import com.xmr.jpa.domain.Girl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Created by xmr on 2018/8/31.
 */
public interface IGirlService {
    Girl saveGirlWithRollBack(Girl girl);

    Girl saveGirlWithoutRollBack(Girl girl);

    void insertTwo();

    List<Girl> findAll();

    Girl save(Girl girl);

    Optional<Girl> findById(Integer id);

    List<Girl> findByAge(Integer age);

    List<Girl> findByName(String name);

    //使用排序
    List<Girl> findByName(String name, Sort sort);

    //使用分页
    List<Girl> findByName(String name, Pageable pageable);

    List<Girl> findByNameLike(String name);

    void deleteById(Integer id);
}
