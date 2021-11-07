package com.chatex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/","/index" })
    public String index() {
        return "index";
    }

    @RequestMapping("/doLogin")
    public String doLogin() {
        return "views/doLogin";
    }

}
