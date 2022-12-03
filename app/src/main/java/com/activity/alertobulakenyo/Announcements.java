package com.activity.alertobulakenyo;

//import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Announcements implements Serializable {

//    @Exclude
//    public String getId() {
//        return id;
//    }
//
//    @Exclude
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Exclude
//    private String id;

    private String anncmntCity;
    private String anncmntDept;
    private String anncmntTitle;
    private String anncmntBody;
    private String anncmntDate;
    private String anncmntDateTime;
    private String anncmntStatus;

    public Announcements () {

    }

    public Announcements(String anncmntCity, String anncmntDept, String anncmntTitle, String anncmntBody, String anncmntDate, String anncmntDateTime, String anncmntStatus) {
        this.anncmntCity = anncmntCity;
        this.anncmntDept = anncmntDept;
        this.anncmntTitle = anncmntTitle;
        this.anncmntBody = anncmntBody;
        this.anncmntDate = anncmntDate;
        this.anncmntDateTime = anncmntDateTime;
        this.anncmntStatus = anncmntStatus;
    }

    public String getAnncmntCity() {
        return anncmntCity;
    }

    public void setAnncmntCity(String anncmntCity) {
        this.anncmntCity = anncmntCity;
    }

    public String getAnncmntDept() {
        return anncmntDept;
    }

    public void setAnncmntDept(String anncmntDept) {
        this.anncmntDept = anncmntDept;
    }

    public String getAnncmntTitle() {
        return anncmntTitle;
    }

    public void setAnncmntTitle(String anncmntTitle) {
        this.anncmntTitle = anncmntTitle;
    }

    public String getAnncmntBody() {
        return anncmntBody;
    }

    public void setAnncmntBody(String anncmntBody) {
        this.anncmntBody = anncmntBody;
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

    public String getAnncmntStatus() {
        return anncmntStatus;
    }

    public void setAnncmntStatus(String anncmntStatus) {
        this.anncmntStatus = anncmntStatus;
    }
}
