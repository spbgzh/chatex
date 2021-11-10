package com.chatex.pojo;

public class HobbyScore {
    private int id;
    private int sports;
    private int music;
    private int travel;
    private int reading;
    private int art;
    private int games;
    private int cartoon;
    private int movie;
    private int cooking;
    private int shopping;



    public HobbyScore(int sports, int music, int travel, int reading, int art, int games, int cartoon, int movie, int cooking, int shopping) {
        this.sports = sports;
        this.music = music;
        this.travel = travel;
        this.reading = reading;
        this.art = art;
        this.games = games;
        this.cartoon = cartoon;
        this.movie = movie;
        this.cooking = cooking;
        this.shopping = shopping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSports() {
        return sports;
    }

    public void setSports(int sports) {
        this.sports = sports;
    }

    public int getMusic() {
        return music;
    }

    public int getTravel() {
        return travel;
    }


    public int getReading() {
        return reading;
    }


    public int getArt() {
        return art;
    }

    public void setArt(int art) {
        this.art = art;
    }

    public int getGames() {
        return games;
    }


    public int getCartoon() {
        return cartoon;
    }


    public int getMovie() {
        return movie;
    }


    public int getCooking() {
        return cooking;
    }


    public int getShopping() {
        return shopping;
    }

}
