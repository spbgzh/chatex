package com.chatex.controller;

import com.chatex.mapper.HobbyScoreMapper;
import com.chatex.mapper.UserIntroductionMapper;
import com.chatex.mapper.UserMapper;
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
    HobbyScoreMapper hobbyScoreMapper;

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
                user.getPhone_number(),
                _user);
        int id = userMapper.getIDByUsername(user.getUsername());
        userIntroductionMapper.insertUserIntroduction(id, "This user said nothing", "This user said nothing");
        hobbyScoreMapper.insertHobbyScores(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        return "redirect:/signUpSuccess";
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
        userIntroductionMapper.updateSomethingToSayByID(idUser, userIntroduction.getAboutMe(), userIntroduction.getSomethingToSay());
        return "redirect:/editInfoSuccess";
    }


    //manageUser
    @GetMapping("/GetManageUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String manageUser(Model model) {
        List<User> listUser = userMapper.getUserList();
        model.addAttribute("listUser", listUser);
        return "/views/manageUser";
    }

    @PostMapping("/setRoleByUsername")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String setRoleByUsername(User user) {
        String userName = user.getUsername();
        if (userMapper.judgeUsernameExist(userName) == 0)
            return "redirect:/setRoleError";
        if (userName.equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            return "redirect:/setRoleError";

        userMapper.updateRoleByUsername(userName, user.getRole());
        return "redirect:/updateUserSuccess";
    }

    @GetMapping("/PostManagementDeleteUser/{tempName}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String managementDeleteUser(@PathVariable("tempName") String userName) {

        if (userMapper.judgeUsernameExist(userName) == 0)
            return "redirect:/deleteUserNotExistError";
        if (userName.equals(SecurityContextHolder.getContext().getAuthentication().getName()))
            return "redirect:/deleteYourselfError";

        int userID = userMapper.getIDByUsername(userName);

        userMapper.deleteUserByID(userID);
        userIntroductionMapper.deleteUserIntroductionByID(userID);
        hobbyScoreMapper.deleteHobbyScores(userID);
        return "redirect:/deleteUserSuccess";
    }




   /* @GetMapping("/page/{username}")
    public String pageUsername(@PathVariable("username") String username, Model model) {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userMapper.getUserByUsername(username);
        model.addAttribute("user", user);
        return "";
    }*/
}
