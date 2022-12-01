package com.activity.alertobulakenyo;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class HotlinesHolder implements Serializable {

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    @Exclude
    private String id;

    private String hotlineCity;
    private String hotlineName;
    private String hotlineOne;
    private String hotlineTwo;
    private String hotlineThree;
    private String hotlineFour;
    private String hotlineFive;

    public HotlinesHolder() {
    }

    public HotlinesHolder(String hotlineCity, String hotlineName, String hotlineOne, String hotlineTwo, String hotlineThree, String hotlineFour, String hotlineFive) {
        this.hotlineCity = hotlineCity;
        this.hotlineName = hotlineName;
        this.hotlineOne = hotlineOne;
        this.hotlineTwo = hotlineTwo;
        this.hotlineThree = hotlineThree;
        this.hotlineFour = hotlineFour;
        this.hotlineFive = hotlineFive;
    }

    public String getHotlineCity() {
        return hotlineCity;
    }

    public void setHotlineCity(String hotlineCity) {
        this.hotlineCity = hotlineCity;
    }

    public String getHotlineName() {
        return hotlineName;
    }

    public void setHotlineName(String hotlineName) {
        this.hotlineName = hotlineName;
    }

    public String getHotlineOne() {
        return hotlineOne;
    }

    public void setHotlineOne(String hotlineOne) {
        this.hotlineOne = hotlineOne;
    }

    public String getHotlineTwo() {
        return hotlineTwo;
    }

    public void setHotlineTwo(String hotlineTwo) {
        this.hotlineTwo = hotlineTwo;
    }

    public String getHotlineThree() {
        return hotlineThree;
    }

    public void setHotlineThree(String hotlineThree) {
        this.hotlineThree = hotlineThree;
    }

    public String getHotlineFour() {
        return hotlineFour;
    }

    public void setHotlineFour(String hotlineFour) {
        this.hotlineFour = hotlineFour;
    }

    public String getHotlineFive() {
        return hotlineFive;
    }

    public void setHotlineFive(String hotlineFive) {
        this.hotlineFive = hotlineFive;
    }
}
