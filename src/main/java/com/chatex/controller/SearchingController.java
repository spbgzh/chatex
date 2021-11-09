package com.chatex.controller;

import com.chatex.mapper.HobbyScoreMapper;
import com.chatex.mapper.UserIntroductionMapper;
import com.chatex.mapper.UserMapper;
import com.chatex.pojo.HobbyScore;
import com.chatex.pojo.SearchParameters;
import com.chatex.pojo.User;
import com.chatex.service.SearchingServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class SearchingController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserIntroductionMapper userIntroductionMapper;
    @Autowired
    HobbyScoreMapper hobbyScoreMapper;
    @Autowired
    SearchingServiceImp searchingServiceImp;

    @RequestMapping("/SearchingPage")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String SearchingPage() {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        HobbyScore hobbyScore = hobbyScoreMapper.getHobbyScoreByID(userMapper.getIDByUsername(loginName));
        if (!HobbyScoreController.judgeModify(hobbyScore)) return "redirect:/GetScore";
        return "views/SearchingPage";
    }

    @GetMapping("/GetSearchingResult")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String GetSearchingResult(SearchParameters searchParameters, Model model) {

        if (searchingServiceImp.isInteger(searchParameters.getMinAge())&&searchingServiceImp.isInteger(searchParameters.getMaxAge())&&Integer.valueOf(searchParameters.getMinAge()) > Integer.valueOf(searchParameters.getMaxAge()))
            return "redirect:/wrongValueError";
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        User loginUser = userMapper.getUserByUsername(loginName);

        List<User> listUser = searchingServiceImp.searchingUsers(loginUser, searchParameters);
        model.addAttribute("listUser", listUser);
        return "views/SearchingResultPage";
    }



}
