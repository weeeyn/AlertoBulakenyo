package com.activity.alertobulakenyo;

//import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class WarningHolder implements Serializable {

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

    private String disasterType;
    private String disasterCity;
    private String disasterBrgy;
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

    public WarningHolder(String disasterType, String disasterCity, String disasterBrgy, String disasterInfo, String eqMagnitude, String fireLevel, String floodLevel, String floodRain, String typhoonName, String typhoonSignal, String disasterDate, String disasterDateTime, String disasterTime) {
        this.disasterType = disasterType;
        this.disasterCity = disasterCity;
        this.disasterBrgy = disasterBrgy;
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

    public String getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(String disasterType) {
        this.disasterType = disasterType;
    }

    public String getDisasterCity() {
        return disasterCity;
    }

    public void setDisasterCity(String disasterCity) {
        this.disasterCity = disasterCity;
    }

    public String getDisasterBrgy() {
        return disasterBrgy;
    }

    public void setDisasterBrgy(String disasterBrgy) {
        this.disasterBrgy = disasterBrgy;
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


