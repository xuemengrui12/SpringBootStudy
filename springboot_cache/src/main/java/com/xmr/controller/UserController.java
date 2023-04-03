package com.xmr.controller;

import com.xmr.pojo.User;
import com.xmr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xmr on 2020/2/23.
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/findById")
    public User findById(@RequestParam("id") Integer id) {
        System.err.println("没有走缓存！" + id);
        return userService.getUser(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public User save(User user) {
        System.out.println(user.toString());
        userService.addUser(user.getName(),user.getSex());
        return user;
    }

    /**
     * @param user
     * @return
     * @CachePut的value属性和cacheNames的作用是一样的，都是标识主键。两个属性不能同时定义，只能定义一个，否则会报错。 key 属性则可以看作为 value 的子键， 一个 value 可以有多个 key 组成不同值存在 Redis 服务器
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(User user) {
        System.out.println(user.toString());
        userService.updateUser(1,user.getName());
        return user;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void remove(Integer id) {
        userService.deleteUserById(id);
    }
}