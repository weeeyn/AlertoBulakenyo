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
    private String hotlineNameAbv;
    private String hotlineOne;
    private String hotlineTwo;
    private String hotlineThree;
    private String hotlineFour;
    private String hotlineFive;
    private String hotlineSix;
    private String hotlineSeven;
    private String hotlineEight;
    private String hotlineNine;
    private String hotlineTen;

    public HotlinesHolder() {
    }

    public HotlinesHolder(String hotlineCity, String hotlineName, String hotlineNameAbv, String hotlineOne, String hotlineTwo, String hotlineThree, String hotlineFour, String hotlineFive, String hotlineSix, String hotlineSeven, String hotlineEight, String hotlineNine, String hotlineTen) {
        this.hotlineCity = hotlineCity;
        this.hotlineName = hotlineName;
        this.hotlineNameAbv = hotlineNameAbv;
        this.hotlineOne = hotlineOne;
        this.hotlineTwo = hotlineTwo;
        this.hotlineThree = hotlineThree;
        this.hotlineFour = hotlineFour;
        this.hotlineFive = hotlineFive;
        this.hotlineSix = hotlineSix;
        this.hotlineSeven = hotlineSeven;
        this.hotlineEight = hotlineEight;
        this.hotlineNine = hotlineNine;
        this.hotlineTen = hotlineTen;
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

    public String getHotlineNameAbv() {
        return hotlineNameAbv;
    }

    public void setHotlineNameAbv(String hotlineNameAbv) {
        this.hotlineNameAbv = hotlineNameAbv;
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

    public String getHotlineSix() {
        return hotlineSix;
    }

    public void setHotlineSix(String hotlineSix) {
        this.hotlineSix = hotlineSix;
    }

    public String getHotlineSeven() {
        return hotlineSeven;
    }

    public void setHotlineSeven(String hotlineSeven) {
        this.hotlineSeven = hotlineSeven;
    }

    public String getHotlineEight() {
        return hotlineEight;
    }

    public void setHotlineEight(String hotlineEight) {
        this.hotlineEight = hotlineEight;
    }

    public String getHotlineNine() {
        return hotlineNine;
    }

    public void setHotlineNine(String hotlineNine) {
        this.hotlineNine = hotlineNine;
    }

    public String getHotlineTen() {
        return hotlineTen;
    }

    public void setHotlineTen(String hotlineTen) {
        this.hotlineTen = hotlineTen;
    }
}
