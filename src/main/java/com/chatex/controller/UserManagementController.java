package com.chatex.controller;

import com.chatex.mapper.UserIntroductionMapper;
import com.chatex.mapper.UserMapper;
import com.chatex.mapper.UserRoleMapper;
import com.chatex.pojo.User;
import com.chatex.pojo.UserIntroduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserManagementController {
    public static final String _admin = "ADMIN";
    public static final String _user = "USER";

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserIntroductionMapper userIntroductionMapper;
    @Autowired
    UserRoleMapper userRoleMapper;

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
        if (userMapper.judgeUsernameExist(user.getUsername()) != 0)
            return "redirect:/usernameExistError";

        userMapper.insertUser(
                user.getAge(),
                user.getUsername(),
                user.getPassword(),
                user.getGender(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number());
        int id = userMapper.getIDByUsername(user.getUsername());
        userRoleMapper.insertUserRole(id, _user);
        userIntroductionMapper.insertUserIntroduction(id, "This user said nothing");
        //！！分数数据库还没有初始化
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
        if (userMapper.judgeUsernameExist(name) == 0)
            return "redirect:/resetPasswordError";

        if (userMapper.getFirst_nameByUsername(name).equals(user.getFirst_name()) &&
                userMapper.getLast_nameByUsername(name).equals(user.getLast_name()) &&
                userMapper.getEmailByUsername(name).equals(user.getEmail()) &&
                userMapper.getPhone_numberByUsername(name).equals(user.getPhone_number())) {
            userMapper.updatePasswordByUsername(name, user.getPassword());
            return "redirect:/resetPasswordSuccess";
        } else return "redirect:/resetPasswordError";
    }


    //mypage
    @GetMapping("/mypage")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String mypage(Model model) {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.getUserByUsername(loginName);
        UserIntroduction userIntroduction = userIntroductionMapper.getUserIntroductionByID(userMapper.getIDByUsername(user.getUsername()));
        model.addAttribute("loginUser", user);
        model.addAttribute("userIntroduction", userIntroduction);
        return "views/mypage";
    }

    @PostMapping("/PostEditBasicInfo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String editBasicInfo(User user) {
        if (userMapper.judgeUsernameExist(user.getUsername()) != 0 &&
                !user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            return "redirect:/editInfoUsernameExistError";
        int id = userMapper.getIDByUsername(user.getUsername());
        userMapper.updateUserByID(id, user.getAge(),
                user.getUsername(),
                user.getGender(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPhone_number());
        return "redirect:/editInfoSuccess";
    }

    @PostMapping("/PostEditIntroductionInfo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String editIntroductionInfo(UserIntroduction userIntroduction) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int idUser = userMapper.getIDByUsername(name);
        userIntroductionMapper.updateSomethingToSayByID(idUser, userIntroduction.getSomethingToSay());
        return "redirect:/editInfoSuccess";
    }


    //manageUser
    @GetMapping("/PostManageUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String manageUser(Model model) {
        List<User> listUser = userMapper.getUserList();
        model.addAttribute("listUser", listUser);
        return "/views/manageUser";
    }

    @PostMapping("/PostManagementDeleteUser")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String managementDeleteUser(User user) {
        String userName = user.getUsername();
        if (userMapper.judgeUsernameExist(userName) == 0)
            return "redirect:/deleteUserNotExistError";
        if (user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            return "redirect:/deleteYourselfError";

        int userID = userMapper.getIDByUsername(userName);

        userMapper.deleteUserByID(userID);
        userIntroductionMapper.deleteUserIntroductionByID(userID);
        userRoleMapper.deleteUserRoleByID(userID);
        //！！删除兴趣分数
        return "redirect:/deleteUserSuccess";
    }


    @GetMapping("/page/{username}")
    public String pageUsername(@PathVariable("username") String username, Model model) {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.getUserByUsername(username);
        model.addAttribute("user", user);
        return "";
    }
}
