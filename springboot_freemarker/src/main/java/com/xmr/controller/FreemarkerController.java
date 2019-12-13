package com.xmr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreemarkerController {

    @RequestMapping("/map")
    public String index(ModelMap map) {
        map.addAttribute("name", "freemarker");
        map.addAttribute("from", "baidu.com");
        // 模版名称，实际的目录为：src/main/resources/templates/thymeleaf.html
        return "freemarker";
    }

    @GetMapping("/mv")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "freemarker");
        mv.addObject("from", "baidu.com");
        // 模版名称，实际的目录为：src/main/resources/templates/thymeleaf.html
        mv.setViewName("freemarker");
        return mv;
    }

}