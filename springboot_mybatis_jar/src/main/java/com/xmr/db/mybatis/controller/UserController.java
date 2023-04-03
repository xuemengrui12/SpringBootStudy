package com.xmr.db.mybatis.controller;

import com.xmr.db.mybatis.domain.User;
import com.xmr.db.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xmr on 2018/3/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService mybatisService;


    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return mybatisService.getUser(id);
    }

    @PostMapping(value = "/add")
    public void addUser(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        mybatisService.addUser(name, sex);
    }

    @PutMapping(value = "/update/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestParam("name") String name) {
        mybatisService.updateUser(id, name);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        mybatisService.deleteUserById(id);
    }
}
