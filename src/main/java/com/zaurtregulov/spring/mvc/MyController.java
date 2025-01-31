package com.zaurtregulov.spring.mvc;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @GetMapping("/")
    public String start() {
        return "start";
    }

    @GetMapping("ask")
    public String ask() {
        return "ask";
    }


    @RequestMapping("show")
    public String show() {
        return "show";
    }


}
