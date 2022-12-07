package com.activity.alertobulakenyo.ObjectClasses;

//import com.google.firebase.firestore.Exclude;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Announcements implements Serializable {

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

    private String anncmntCity;
    private String title;
    private String body;
    private String anncmntDate;
    private String anncmntDateTime;

    public Announcements () {

    }

    public Announcements(String anncmntCity, String title, String body, String anncmntDate, String anncmntDateTime) {
        this.anncmntCity = anncmntCity;
        this.title = title;
        this.body = body;
        this.anncmntDate = anncmntDate;
        this.anncmntDateTime = anncmntDateTime;
    }

    public String getAnncmntCity() {
        return anncmntCity;
    }

    public void setAnncmntCity(String anncmntCity) {
        this.anncmntCity = anncmntCity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAnncmntDate() {
        return anncmntDate;
    }

    public void setAnncmntDate(String anncmntDate) {
        this.anncmntDate = anncmntDate;
    }

    public String getAnncmntDateTime() {
        return anncmntDateTime;
    }

    public void setAnncmntDateTime(String anncmntDateTime) {
        this.anncmntDateTime = anncmntDateTime;
    }

}
