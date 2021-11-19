package com.chatex.pojo;

public class UserLikeDislike {
    private int id;
    private int userLike;
    private int userDislike;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserLike() {
        return userLike;
    }

    public int getUserDislike() {
        return userDislike;
    }

    public UserLikeDislike(int userLike, int userDislike) {
        this.userLike = userLike;
        this.userDislike = userDislike;
    }
}
