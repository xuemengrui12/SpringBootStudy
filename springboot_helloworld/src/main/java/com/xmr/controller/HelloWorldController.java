package com.xmr.controller;

import com.xmr.domain.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

//@RestController等价于@Controller和@ResponseBody的组合
@RestController
public class HelloWorldController {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    //注入对象
    @Autowired
    private Environment env;
    @Autowired
    private Hello hello;
    private final static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

    //调用方式为url?id=
    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String hello1(@RequestParam("id") Integer id) {
        logger.info("index==" + id);

        return "index==" + id;
    }

    //调用方式为：url/{id}
    @RequestMapping(value = "/hello1/{id}", method = RequestMethod.GET)
    public String hello2(@PathVariable("id") Integer id) {
        return "index==" + id;
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public String content() {
//        return name + "　3333" + age;
        return content;
    }

    @RequestMapping("/say")
    public String sayHello() {
        return hello.sayHello();
    }

    @RequestMapping("/getHello")
    public Hello getHello() {
        Hello hello=new Hello();
        hello.setMsg("hello");
        return hello;
    }

    @RequestMapping("/index")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://www.baidu.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "index";
    }

    @RequestMapping("/env")
    public String env() {
        String port = env.getProperty("server.port");
        return port;
    }
}