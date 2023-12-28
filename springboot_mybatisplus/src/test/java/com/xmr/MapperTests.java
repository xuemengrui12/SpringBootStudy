package com.xmr;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmr.entity.User;
import com.xmr.mapper.UserMapper;
import com.xmr.service.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MapperTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user=new User();
        user.setAge(22);
        user.setEmail("bded@126.com");
        user.setName("john");
        user.setId(5L);
        userMapper.insert(user);
    }
    @Test
    public void testDelete(){
        userMapper.delete(new QueryWrapper<User>().eq("id",5));
    }
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);

        System.out.println(userMapper.selectById(1));
    }
    // 根据 Wrapper 条件，查询全部记录。注意： 只返回第一个字段的值
    @Test
    public void testSelectObjs() {
        System.out.println(("----- selectAll method test ------"));
        List<Object> userList = userMapper.selectObjs(new QueryWrapper<User>().ge("id",3));
//        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);

    }
}
