package com.xmr.controller;

import com.xmr.domain.SysUser;
import com.xmr.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xmr on 2020/2/16.
 */
@RestController
public class TestController {

    @Autowired
    private LoginService loginService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("test")
    public String test() {
        return "test";
    }

    @PreAuthorize("hasAuthority('permission1')")
    @GetMapping("authenticated/getData")
    public String getAuthenticatedData() {
        return "authenticatedData";
    }

    @GetMapping("admin/getData")
    public String getAdminData() {
        return "adminData";
    }

    @GetMapping("guest/getData")
    public String getGuestData() {
        return "guestData";
    }

    @GetMapping("permission1/getData")
    public String getPermission1Data() {
        return "permission1Data";
    }

    @GetMapping("permission2/getData")
    public String getPermission2Data() {
        return "permission2Data";
    }

    @GetMapping("permission3/getData")
    public String getPermission3Data() {
        return "permission3Data";
    }

    @GetMapping("permission4/getData")
    public String getPermission4Data() {
        return "permission4Data";
    }


    @PostMapping("user/login")
    public String login(@RequestBody SysUser user) {
        return loginService.login(user);
    }

    @GetMapping("user/logout")
    public String logout() {
        return loginService.logout();
    }
}
