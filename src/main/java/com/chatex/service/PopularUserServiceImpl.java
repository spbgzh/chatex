package com.chatex.service;

import com.chatex.mapper.UserLikeDislikeMapper;
import com.chatex.mapper.UserMapper;
import com.chatex.pojo.User;
import com.chatex.pojo.UserLikeDislike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PopularUserServiceImpl implements PopularUserService {

    @Autowired
    UserLikeDislikeMapper userLikeDislikeMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getPopularUsers() {
        List<UserLikeDislike> listUserLikeDislike = userLikeDislikeMapper.getUserLikeDislikeList();
        Map<Integer, Integer> mapTotalId = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : 1;
            }

        });
        for (UserLikeDislike userLikeDislike : listUserLikeDislike) {
            mapTotalId.put( userLikeDislike.getUserLike() - userLikeDislike.getUserDislike(),userLikeDislike.getId());
        }

        List<User> listUser = new ArrayList<>();
        List<Integer> listID = mapTotalId.values().stream().limit(10).toList();
        for (Integer integer : listID) {
            listUser.add(userMapper.getUserByID(integer));
        }
        return listUser;
    }

    @Override
    public List<UserLikeDislike> popularPopularUsersLikeDislike(List<User> listUser) {
        List<UserLikeDislike> list=new ArrayList<>();
        for (User user : listUser) {
            list.add(userLikeDislikeMapper.getUserLikeDislikeByID(user.getId()));
        }
        return list;


    }
}
