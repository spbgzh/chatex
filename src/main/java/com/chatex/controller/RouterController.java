package com.chatex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "../static/index";
    }

    @RequestMapping("/guides")
    public String guides(){
        return "views/guides";
    }
}
