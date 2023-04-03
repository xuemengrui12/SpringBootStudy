package com.xmr.mybatis.controller;

import com.xmr.mybatis.model.Account;
import com.xmr.mybatis.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by xmr on 2020/2/23.
 */
@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public String login(HttpServletRequest request, String name, String password) {
//    public String login( String name, String password) {
        Account account = accountService.getAccountByName(name);
        if (account != null && account.getPwd().equals(password)) {
            request.getSession().setAttribute("userAccount", account);
            return "登录成功";
        }

        return "登录失败";
    }
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userAccount");
        return "退出登录";
    }
}