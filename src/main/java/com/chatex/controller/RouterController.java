package com.chatex.controller;

import com.chatex.pojo.User;
import com.chatex.mapper.UserManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouterController {
    public static final String _admin = "ADMIN";
    public static final String _user = "USER";

    @Autowired
    UserManageMapper userManageMapper;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "../static/index";
    }

    @RequestMapping("/doLogin")
    public String doLogin() {
        return "views/doLogin";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "views/signup";
    }

    @PostMapping("/PostSignUp")
    public String postSignUp(User user) {
        if (userManageMapper.judgeUsernameExist(user.getUsername()) != 0)
            //return "redirect:/UsernameNotExistERROR";
            return "redirect:/signup";

        userManageMapper.insertUser(user.getUsername(),
                user.getPassword(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number());
        userManageMapper.insertUserRole(userManageMapper.getIDByUsername(user.getUsername()), _user);
        return "redirect:/doLogin";
    }

    @RequestMapping("/password_reset")
    public String password_reset() {
        return "views/password_reset";
    }

    @PostMapping("/PasswordReset")
    public String postPasswordReset(User user) {
        String name = user.getUsername();
        if (userManageMapper.judgeUsernameExist(name) == 0)
            //return "redirect:/UsernameNotExistERROR";
            return "redirect:/password_reset";

        if (userManageMapper.getFirst_nameByUsername(name).equals(user.getFirst_name()) &&
                userManageMapper.getLast_nameByUsername(name).equals(user.getLast_name()) &&
                userManageMapper.getEmailByUsername(name).equals(user.getEmail()) &&
                userManageMapper.getPhone_numberByUsername(name).equals(user.getPhone_number())) {

            userManageMapper.updatePasswordByUsername(name, user.getPassword());
            return "redirect:/signup";
        } else return "redirect:/password_reset";

    }
}
