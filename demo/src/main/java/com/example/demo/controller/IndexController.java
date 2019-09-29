package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    //返回一个String的返回值，框架会自动在 templates目录下找到与返回值对应的html，后由Thymeleaf渲染出来
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}