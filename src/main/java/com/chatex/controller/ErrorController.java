package com.chatex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @RequestMapping("/loginError")
    public String loginError() {
        return "../static/error/loginError";
    }
    @RequestMapping("/usernameExistError")
    public String usernameExistError() {
        return "../static/error/usernameExistError";
    }

    @RequestMapping("/resetPasswordError")
    public String resetPasswordError() {
        return "../static/error/resetPasswordError";
    }
}
