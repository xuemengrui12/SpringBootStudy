package com.xmr.controller;

import com.xmr.dao.IUserDao;
import com.xmr.domain.User;
import com.xmr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xmr on 2020/2/23.
 */
@RestController
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/testredistran1")
    public void testRedisTran1() {
        //开启事务
        redisTemplate.multi();
        redisTemplate.opsForValue().set("test1a", "test1a");
        redisTemplate.opsForValue().set(null, "test1b");
        redisTemplate.opsForValue().set("test1c", "test1c");
        //关闭事务
        redisTemplate.exec();
    }

    @RequestMapping(value = "/testredistran2")
    public String testRedisTran2() {
        SessionCallback sessionCallback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.multi();
                redisOperations.opsForValue().set("test2a", "test2a");
                redisOperations.opsForValue().set(null, "test2b");
                redisOperations.opsForValue().set("test2c", "test2c");
                return redisOperations.exec();
            }
        };

        return "success";
    }

    @Transactional
    @RequestMapping(value = "/testredistran3")
    public String testRedisTran3() {
        redisTemplate.opsForValue().set("test3a", "test3a");
        redisTemplate.opsForValue().set(null, "test3b");
        redisTemplate.opsForValue().set("test3c", "test3c");
        return "2";
    }


    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        return sessionId;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        User loaded = userService.getUser(1);
        System.out.println("loaded=" + loaded);
        User cached = userService.getUser(1);
        System.out.println("cached=" + cached);
        loaded = userService.getUser(2);
        System.out.println("loaded2=" + loaded);
        return "ok";
    }

    @RequestMapping("/delete")
    public @ResponseBody String delete(Integer id){
        userService.deleteUserById(id);
        return"ok";
    }
    @RequestMapping("/test1")
    public@ResponseBody String test1(){
        userService.test();
        System.out.println("DemoInfoController.test1()");
        return"ok";
    }
}