package com.xmr.controller;

import com.xmr.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xmr on 2020/2/23.
 */
@RestController
public class UserController {

    @RequestMapping("/getUser")
    public User getUser() {
        User user=new User("xmr", 30);
        System.out.println(user.toString());
        return user;
    }
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public Object sessions (HttpServletRequest request){
        HttpSession session=request.getSession();
        String sessionId =  session.getId();
        return sessionId;
    }
}