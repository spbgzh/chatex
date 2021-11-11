package com.chatex.service;

import com.chatex.pojo.User;
import com.chatex.pojo.UserLikeDislike;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PopularUserService {
    List<User> getPopularUsers();

    List<UserLikeDislike> popularPopularUsersLikeDislike(List<User> list);
}
