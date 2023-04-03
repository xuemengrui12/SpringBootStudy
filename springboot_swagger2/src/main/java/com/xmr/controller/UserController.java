package com.xmr.controller;

import com.xmr.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "UserController")
@RestController
public class UserController {



    @ApiOperation(value="用户登录", notes="用户登录接口", produces="application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true ,dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true ,dataType = "String")
    })
    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET})
//    @ResponseBody
    public ModelMap login(User data, HttpServletRequest request){
        // todo 实现
        return null;
    }

//    @RequestMapping("/")
//    public String index() {
//        return "Hello World";
//    }
//
//    //调用方式为url?id=
//    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
//    public String hello1(@RequestParam("id") Integer id) {
//        return "index==" + id;
//    }
//
//    //调用方式为：url/{id}
//    @RequestMapping(value = "/hello1/{id}", method = RequestMethod.GET)
//    public String hello2(@PathVariable("id") Integer id) {
//        return "index==" + id;
//    }

}