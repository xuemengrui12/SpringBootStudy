package com.xmr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController等价于@Controller和@ResponseBody的组合
@RestController
public class TestController {
    @GetMapping("test")
    public String test() {
        return "test";
    }

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

}