package com.chatex.service;

import com.chatex.mapper.HobbyScoreMapper;
import com.chatex.mapper.UserIntroductionMapper;
import com.chatex.mapper.UserMapper;
import com.chatex.pojo.HobbyScore;
import com.chatex.pojo.SearchParameters;
import com.chatex.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

@Component
public class SearchingServiceImp implements SearchingService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserIntroductionMapper userIntroductionMapper;
    @Autowired
    HobbyScoreMapper hobbyScoreMapper;

    public List<User> searchingUsers(User user, SearchParameters searchParameters) {
        int userID = user.getId();
        //符合要求的listUser
        List<User> listUser = getListUser(userID, searchParameters);
        Map<User, Double> mapUserDistance = getMapUserEuclideanMetric(userID, listUser);
        List<User> result = mapUserDistance.keySet().stream().limit(5).toList();
        return result;
    }

    private List<User> getListUser(int id, SearchParameters searchParameters) {
        List<User> result;
        if (searchParameters.getGenderRestriction().equals( "man") || searchParameters.getGenderRestriction() .equals("woman"))
            result = userMapper.getUserListWithoutIdGenderRestriction(id, searchParameters.getMinAge(), searchParameters.getMaxAge(), searchParameters.getGenderRestriction());
        else
            result = userMapper.getUserListWithoutId(id, searchParameters.getMinAge(), searchParameters.getMaxAge());
        return result;
    }

    private  Map<User, Double> getMapUserEuclideanMetric(int id, List<User> userList) {
        Map<User, Double> map = new TreeMap<>(){

        };
        HobbyScore userScore = hobbyScoreMapper.getHobbyScoreByID(id);
        for (User user : userList) {
            HobbyScore temp=hobbyScoreMapper.getHobbyScoreByID(user.getId());
            double tempDistance = Math.sqrt(pow2(userScore.getArt() - temp.getArt()) +
                    pow2(userScore.getCartoon() - temp.getCartoon()) +
                    pow2(userScore.getCooking() - temp.getCooking()) +
                    pow2(userScore.getGames() - temp.getGames()) +
                    pow2(userScore.getMovie() - temp.getMovie()) +
                    pow2(userScore.getTravel() - temp.getTravel()) +
                    pow2(userScore.getMusic() - temp.getMusic()) +
                    pow2(userScore.getReading() - temp.getReading()) +
                    pow2(userScore.getShopping() - temp.getShopping()) +
                    pow2(userScore.getSports() - temp.getSports()));
            map.put(user,tempDistance);

        }
        return map;
    }

    private double pow2(Integer num) {
        return num * num;
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}

