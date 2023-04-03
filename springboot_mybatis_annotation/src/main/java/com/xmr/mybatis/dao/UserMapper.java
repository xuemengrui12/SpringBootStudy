package com.xmr.mybatis.dao;

import com.xmr.mybatis.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "userSex", column = "sex"),
            @Result(property = "nickName", column = "name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex", column = "sex"),
            @Result(property = "nickName", column = "name")
    })
    User getOne(Long id);

    @Insert("INSERT INTO users(user_name,password,sex) VALUES(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("UPDATE users SET user_name=#{userName},name=#{nickName} WHERE id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(Long id);

}