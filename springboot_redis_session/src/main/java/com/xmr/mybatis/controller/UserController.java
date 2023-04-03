package com.xmr.mybatis.controller;

import com.xmr.mybatis.model.User;
import com.xmr.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users = iUserService.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(Long id) {
        User user = iUserService.getOne(id);
        return user;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void save(User user) {
        iUserService.insert(user);
    }

    @RequestMapping(value = "update")
    public void update(User user) {
        iUserService.update(user);
    }

    @RequestMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        iUserService.delete(id);
    }


}