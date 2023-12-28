package com.xmr;

import com.xmr.mapper.UserMapper;
import com.xmr.entity.User;
import com.xmr.service.IUserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserService userService;
    @Test
    public void testQueryAll() {
        System.out.println(("----- queryAll method test ------"));
        List<User> userList = userMapper.queryAll();
        userList.forEach(System.out::println);

    }

    @Test
    public void testSave(){
        User user=new User();
        user.setAge(22);
        user.setEmail("bded@126.com");
        user.setName("john");
        userService.save(user);
    }
    @Test
    public void testInsertBatchSomeColumn(){
        List<User> list=new ArrayList<>();
        User user=new User();
        user.setAge(221);
        user.setEmail("bded@126.com");
        user.setName("john");
        User user1=new User();
        user1.setAge(321);
        user1.setEmail("bded@163.com");
        user1.setName("jam");
        list.add(user);
        list.add(user1);
        userMapper.insertBatchSomeColumn(list);
    }

}
