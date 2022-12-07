package com.activity.alertobulakenyo.ObjectClasses;

public class Residents {

    String resFname, resLname, resUsername, resContact, resHouse, resCity, resBrgy, resEmail, resUserId, userType;

    public Residents() {
    }

    public Residents(String resFname, String resLname, String resUsername, String resContact, String resHouse, String resCity, String resBrgy, String resEmail, String resUserId, String userType) {
        this.resFname = resFname;
        this.resLname = resLname;
        this.resUsername = resUsername;
        this.resContact = resContact;
        this.resHouse = resHouse;
        this.resCity = resCity;
        this.resBrgy = resBrgy;
        this.resEmail = resEmail;
        this.resUserId = resUserId;
        this.userType = userType;
    }

    public String getResFname() {
        return resFname;
    }

    public void setResFname(String resFname) {
        this.resFname = resFname;
    }

    public String getResLname() {
        return resLname;
    }

    public void setResLname(String resLname) {
        this.resLname = resLname;
    }

    public String getResUsername() {
        return resUsername;
    }

    public void setResUsername(String resUsername) {
        this.resUsername = resUsername;
    }

    public String getResContact() {
        return resContact;
    }

    public void setResContact(String resContact) {
        this.resContact = resContact;
    }

    public String getResHouse() {
        return resHouse;
    }

    public void setResHouse(String resHouse) {
        this.resHouse = resHouse;
    }

    public String getResCity() {
        return resCity;
    }

    public void setResCity(String resCity) {
        this.resCity = resCity;
    }

    public String getResBrgy() {
        return resBrgy;
    }

    public void setResBrgy(String resBrgy) {
        this.resBrgy = resBrgy;
    }

    public String getResEmail() {
        return resEmail;
    }

    public void setResEmail(String resEmail) {
        this.resEmail = resEmail;
    }

    public String getResUserId() {
        return resUserId;
    }

    public void setResUserId(String resUserId) {
        this.resUserId = resUserId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
