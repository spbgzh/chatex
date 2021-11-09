package com.chatex.service;

import com.chatex.pojo.SearchParameters;
import com.chatex.pojo.User;

import java.util.List;

public interface SearchingService {
    public List<User> searchingUsers(User user, SearchParameters searchParameters);


}
