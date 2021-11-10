package com.chatex.pojo;

public class SearchParameters {
    private String minAge;
    private String maxAge;
    private String genderRestriction;

    public String getMinAge() {
        return minAge;
    }


    public String getMaxAge() {
        return maxAge;
    }


    public String getGenderRestriction() {
        return genderRestriction;
    }


    public SearchParameters(String minAge, String maxAge, String genderRestriction) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.genderRestriction = genderRestriction;
    }
}
