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

@Component
public class SearchingServiceImpl implements SearchingService {

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
        Map<Double, User> mapUserDistance = getMapUserEuclideanMetric(userID, listUser);
        return mapUserDistance.values().stream().limit(6).toList();
    }

    private List<User> getListUser(int id, SearchParameters searchParameters) {
        List<User> result;
        if (searchParameters.getGenderRestriction().equals("man") || searchParameters.getGenderRestriction().equals("woman"))
            result = userMapper.getUserListWithoutIdGenderRestriction(id, Integer.parseInt(searchParameters.getMinAge()), Integer.parseInt(searchParameters.getMaxAge()), searchParameters.getGenderRestriction());
        else
            result = userMapper.getUserListWithoutId(id, Integer.parseInt(searchParameters.getMinAge()), Integer.parseInt(searchParameters.getMaxAge()));
        return result;
    }

    private Map<Double, User> getMapUserEuclideanMetric(int id, List<User> userList) {
        Map<Double, User> map = new TreeMap<>();
        HobbyScore userScore = hobbyScoreMapper.getHobbyScoreByID(id);
        for (User user : userList) {
            HobbyScore temp = hobbyScoreMapper.getHobbyScoreByID(user.getId());
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
            map.put( tempDistance,user);

        }
        return map;
    }

    private double pow2(Integer num) {
        return num * num;
    }

}

