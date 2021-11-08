package com.chatex.controller;

import com.chatex.mapper.UserManageMapper;
import com.chatex.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninSignupAndPasswordResetController {
    public static final String _admin = "ADMIN";
    public static final String _user = "USER";

    @Autowired
    UserManageMapper userManageMapper;

    //Sign-in
    @RequestMapping("/doLogin")
    public String doLogin() {
        return "views/doLogin";
    }


    //Sign-up
    @RequestMapping("/signup")
    public String signup() {
        return "views/signup";
    }

    @PostMapping("/PostSignUp")
    public String postSignUp(User user) {
        if (userManageMapper.judgeUsernameExist(user.getUsername()) != 0)
            //return "redirect:/UsernameNotExistERROR";
            return "redirect:/usernameExistError";

        userManageMapper.insertUser(
                user.getAge(),
                user.getUsername(),
                user.getPassword(),
                user.getGender(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number());
        userManageMapper.insertUserRole(userManageMapper.getIDByUsername(user.getUsername()), _user);
        return "redirect:/doLogin";
    }


    //Password Reset
    @RequestMapping("/password_reset")
    public String password_reset() {
        return "views/password_reset";
    }

    @PostMapping("/PasswordReset")
    public String postPasswordReset(User user) {
        String name = user.getUsername();
        if (userManageMapper.judgeUsernameExist(name) == 0)
            //return "redirect:/UsernameNotExistERROR";
            return "redirect:/resetPasswordError";

        if (userManageMapper.getFirst_nameByUsername(name).equals(user.getFirst_name()) &&
                userManageMapper.getLast_nameByUsername(name).equals(user.getLast_name()) &&
                userManageMapper.getEmailByUsername(name).equals(user.getEmail()) &&
                userManageMapper.getPhone_numberByUsername(name).equals(user.getPhone_number())) {

            userManageMapper.updatePasswordByUsername(name, user.getPassword());
            return "redirect:/signup";
        } else return "redirect:/resetPasswordError";
    }
}
