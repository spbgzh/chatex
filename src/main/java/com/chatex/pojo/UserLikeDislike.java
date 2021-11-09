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

    public void setUserLike(int userLike) {
        this.userLike = userLike;
    }

    public int getUserDislike() {
        return userDislike;
    }

    public void setUserDislike(int userDislike) {
        this.userDislike = userDislike;
    }

    public UserLikeDislike(int userLike, int userDislike) {
        this.userLike = userLike;
        this.userDislike = userDislike;
    }
}
