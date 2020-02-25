package com.xmr.jpa.repository;

import com.xmr.jpa.domain.Girl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by xmr on 2018/3/14.
 */
public interface GirlRepository extends CrudRepository<Girl, Integer> {
    /*通过年龄相等查询，参数为age
     * 相当于JPQL:select g from Girl g whrer g.age=?1*/
    List<Girl> findByAge(Integer age);

    /*使用NamedQuery里定义的查询语句，而不是根据方法名称查询*/
    List<Girl> findByName(String name);

    //使用排序
    List<Girl> findByName(String name, Sort sort);

    //使用分页
    List<Girl> findByName(String name, Pageable pageable);


    List<Girl> findByNameLike(String name);

    List<Girl> findFirst10Byname(String name);

    @Override
    @Query("select g from Girl  g where g.id=?1")
    Optional<Girl> findById(Integer id);

    @Query("select g from Girl  g where  g.age=:age")
    Optional<Girl> findByIdAndAge(@Param("age") Integer age);

    @Modifying
    @Transactional
    @Query("update Girl g set g.name=?1")
    int setName(String name);


}


