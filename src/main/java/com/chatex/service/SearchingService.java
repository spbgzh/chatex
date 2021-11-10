package com.chatex.service;

import com.chatex.pojo.SearchParameters;
import com.chatex.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchingService {
    List<User> searchingUsers(User user, SearchParameters searchParameters);

    static boolean judgeNumber(String strMin, String strMax) {
        return strMin != null && strMax != null &&
                !strMin.equals("") && !strMax.equals("") &&
                strMin.chars().allMatch(Character::isDigit) &&
                strMax.chars().allMatch(Character::isDigit) &&
                Integer.parseInt(strMax) >= Integer.parseInt(strMin);
    }
}
