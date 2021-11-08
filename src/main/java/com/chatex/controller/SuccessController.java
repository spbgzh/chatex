package com.chatex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccessController {
    @RequestMapping("/resetPasswordSuccess")
    public String resetPasswordSuccess() {
        return "../static/success/resetPasswordSuccess";
    }


    @RequestMapping("/editInfoSuccess")
    public String editInfoSuccess() {
        return "../static/success/editInfoSuccess";
    }

    @RequestMapping("/deleteUserSuccess")
    public String deleteUserSuccess() {
        return "../static/success/deleteUserSuccess";
    }
}
