package com.activity.alertobulakenyo.ObjectClasses;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class WarningHolder implements Serializable {

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

    private String title;
    private String body;
    private String disasterInfo;
    private String eqMagnitude;
    private String fireLevel;
    private String floodLevel;
    private String floodRain;
    private String typhoonName;
    private String typhoonSignal;
    private String disasterDate;
    private String disasterDateTime;
    private String disasterTime;


    public WarningHolder() {
    }

    public WarningHolder(String title, String body, String disasterInfo, String eqMagnitude, String fireLevel, String floodLevel, String floodRain, String typhoonName, String typhoonSignal, String disasterDate, String disasterDateTime, String disasterTime) {
        this.title = title;
        this.body = body;
        this.disasterInfo = disasterInfo;
        this.eqMagnitude = eqMagnitude;
        this.fireLevel = fireLevel;
        this.floodLevel = floodLevel;
        this.floodRain = floodRain;
        this.typhoonName = typhoonName;
        this.typhoonSignal = typhoonSignal;
        this.disasterDate = disasterDate;
        this.disasterDateTime = disasterDateTime;
        this.disasterTime = disasterTime;
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

    public String getDisasterInfo() {
        return disasterInfo;
    }

    public void setDisasterInfo(String disasterInfo) {
        this.disasterInfo = disasterInfo;
    }

    public String getEqMagnitude() {
        return eqMagnitude;
    }

    public void setEqMagnitude(String eqMagnitude) {
        this.eqMagnitude = eqMagnitude;
    }

    public String getFireLevel() {
        return fireLevel;
    }

    public void setFireLevel(String fireLevel) {
        this.fireLevel = fireLevel;
    }

    public String getFloodLevel() {
        return floodLevel;
    }

    public void setFloodLevel(String floodLevel) {
        this.floodLevel = floodLevel;
    }

    public String getFloodRain() {
        return floodRain;
    }

    public void setFloodRain(String floodRain) {
        this.floodRain = floodRain;
    }

    public String getTyphoonName() {
        return typhoonName;
    }

    public void setTyphoonName(String typhoonName) {
        this.typhoonName = typhoonName;
    }

    public String getTyphoonSignal() {
        return typhoonSignal;
    }

    public void setTyphoonSignal(String typhoonSignal) {
        this.typhoonSignal = typhoonSignal;
    }

    public String getDisasterDate() {
        return disasterDate;
    }

    public void setDisasterDate(String disasterDate) {
        this.disasterDate = disasterDate;
    }

    public String getDisasterDateTime() {
        return disasterDateTime;
    }

    public void setDisasterDateTime(String disasterDateTime) {
        this.disasterDateTime = disasterDateTime;
    }

    public String getDisasterTime() {
        return disasterTime;
    }

    public void setDisasterTime(String disasterTime) {
        this.disasterTime = disasterTime;
    }
}


