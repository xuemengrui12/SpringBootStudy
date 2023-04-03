package com.xmr.jdbc.controller;

import com.xmr.jdbc.domain.User;
import com.xmr.jdbc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xmr on 2018/3/15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/hello")
    public User hello() {
        User user = new User();
        user.setName("afa1f");
        user.setSex("m");
        return user;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public User getUser(@RequestParam("id") Integer id) {
//        return userService.getUser(id);
//    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/add")
    public void addUser(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        userService.addUser(name, sex);
    }

    @PutMapping(value = "/update/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestParam("name") String name) {
        userService.updateUser(id, name);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
    }
}
