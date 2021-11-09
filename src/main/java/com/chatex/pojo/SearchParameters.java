package com.chatex.pojo;

public class SearchParameters {
    private String minAge;
    private String maxAge;
    private String genderRestriction;

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(String maxAge) {
        this.maxAge = maxAge;
    }

    public String getGenderRestriction() {
        return genderRestriction;
    }

    public void setGenderRestriction(String genderRestriction) {
        this.genderRestriction = genderRestriction;
    }

    public SearchParameters(String minAge, String maxAge, String genderRestriction) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.genderRestriction = genderRestriction;
    }
}
