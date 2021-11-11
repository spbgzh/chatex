package com.chatex.controller;

import com.chatex.mapper.HobbyScoreMapper;
import com.chatex.mapper.UserMapper;
import com.chatex.pojo.HobbyScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HobbyScoreController {
    @Autowired
    HobbyScoreMapper hobbyScoreMapper;
    @Autowired
    UserMapper userMapper;

    @GetMapping("/GetScore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String GetScore(Model model) {
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        HobbyScore hobbyScore = hobbyScoreMapper.getHobbyScoreByID(userMapper.getIDByUsername(loginName));
        model.addAttribute("hobbyScore", hobbyScore);
        return "views/scorepage";
    }

    @PostMapping("/PostScore")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String PostScore(HobbyScore hobbyScore) {
        if (judgeModify(hobbyScore))
            return "redirect:/PostScoreError";
        String loginName = SecurityContextHolder.getContext().getAuthentication().getName();
        hobbyScoreMapper.updateScore(userMapper.getIDByUsername(loginName),
                hobbyScore.getSports(),
                hobbyScore.getMusic(),
                hobbyScore.getTravel(),
                hobbyScore.getReading(),
                hobbyScore.getArt(),
                hobbyScore.getMovie(),
                hobbyScore.getCartoon(),
                hobbyScore.getGames(),
                hobbyScore.getCooking(),
                hobbyScore.getShopping());
        return "redirect:/PostScoreSuccess";
    }

    public static boolean judgeModify(HobbyScore h) {
        return h.getArt() == 0 && h.getCartoon() == 0 && h.getCooking() == 0 && h.getGames() == 0 && h.getMovie() == 0 && h.getMusic() == 0 && h.getReading() == 0 && h.getShopping() == 0 && h.getSports() == 0 && h.getTravel() == 0;
    }

}
