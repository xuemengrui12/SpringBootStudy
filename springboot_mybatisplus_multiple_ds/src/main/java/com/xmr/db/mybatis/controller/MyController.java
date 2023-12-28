package com.xmr.db.mybatis.controller;


import com.xmr.db.mybatis.domain.Stu;
import com.xmr.db.mybatis.domain.User;
import com.xmr.db.mybatis.service.IStuService;
import com.xmr.db.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xmr on 2020/1/15.
 */
@RestController
public class MyController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IStuService stuService;

    //    @RequestMapping(method = RequestMethod.GET)
    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping(value = "/user/add")
    public void addUser(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        userService.addUser(name, sex);
    }

    @PutMapping(value = "/user/update/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestParam("name") String name) {
        userService.updateUser(id, name);
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
    }

    @GetMapping(value = "/stu/{id}")
    public Stu getStu(@PathVariable("id") Long id) {
        return stuService.getStu(id);
    }

    @PostMapping(value = "/stu/add")
    public void addStu(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        stuService.addStu(name, sex);
    }

    @PutMapping(value = "/stu/update/{id}")
    public void updateStu(@PathVariable("id") Integer id, @RequestParam("name") String name) {
        stuService.updateStu(id, name);
    }

    @DeleteMapping(value = "/stu/delete/{id}")
    public void deleteStu(@PathVariable("id") Long id) {
        stuService.deleteStuById(id);
    }

    @Transactional(transactionManager = "secondaryTransactionManager", rollbackFor = {Exception.class})
    @PostMapping(value = "/add")
    public void add(@RequestParam("name") String name, @RequestParam("sex") String sex) {
        userService.addUser(name,sex);
        int i=1/0;
        stuService.addStu(name, sex);

    }
}
