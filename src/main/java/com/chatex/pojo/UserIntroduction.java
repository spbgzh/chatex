package com.chatex.pojo;

public class UserIntroduction {
    private int id;
    private String somethingToSay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSomethingToSay() {
        return somethingToSay;
    }

    public void setSomethingToSay(String somethingToSay) {
        this.somethingToSay = somethingToSay;
    }

    public UserIntroduction( String somethingToSay) {
        this.somethingToSay = somethingToSay;
    }
}
