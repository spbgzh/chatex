package com.chatex.pojo;

public class UserIntroduction {
    private int id;
    private String aboutMe;
    private String somethingToSay;

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

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

    public UserIntroduction(String aboutMe, String somethingToSay) {
        this.aboutMe = aboutMe;
        this.somethingToSay = somethingToSay;
    }
}
