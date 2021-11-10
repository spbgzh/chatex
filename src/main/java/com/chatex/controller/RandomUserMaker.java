package com.chatex.controller;

import com.chatex.mapper.HobbyScoreMapper;
import com.chatex.mapper.UserIntroductionMapper;
import com.chatex.mapper.UserLikeDislikeMapper;
import com.chatex.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;


@Controller
public class RandomUserMaker {

    private static final String _user = "USER";
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserIntroductionMapper userIntroductionMapper;
    @Autowired
    HobbyScoreMapper hobbyScoreMapper;
    @Autowired
    UserLikeDislikeMapper userLikeDislikeMapper;

    @RequestMapping("/RandomAddUser")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String RandomAddUser() {
        Random r = new Random();
        for (int i = 1; i <= 1000; i++) {
            userMapper.insertUser(String.valueOf(r.nextInt(100)),
                    "Test" + i,
                    String.valueOf(123),
                    r.nextBoolean() ? "man" : "woman",
                    "test", "test",
                    "test", "test", _user
            );
            int tempId = userMapper.getIDByUsername("Test" + i);
            userIntroductionMapper.insertUserIntroduction(tempId, "test", "test");
            hobbyScoreMapper.insertHobbyScores(r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10), r.nextInt(10));
            userLikeDislikeMapper.insertUserLikeDislike(r.nextInt(20),r.nextInt(10));
        }
        return "redirect:/";
    }
}
