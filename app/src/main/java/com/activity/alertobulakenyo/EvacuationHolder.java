package com.activity.alertobulakenyo;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class EvacuationHolder implements Serializable {

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

    private String evacuationName;
    private String evacuationAddress;
    private String evacuationLongitude;
    private String evacuationLatitude;
    private String evacuationCity;
    private String evacuationBrgy;

    public EvacuationHolder() {
    }

    public EvacuationHolder(String evacuationName, String evacuationAddress, String evacuationLongitude, String evacuationLatitude, String evacuationCity, String evacuationBrgy) {
        this.evacuationName = evacuationName;
        this.evacuationAddress = evacuationAddress;
        this.evacuationLongitude = evacuationLongitude;
        this.evacuationLatitude = evacuationLatitude;
        this.evacuationCity = evacuationCity;
        this.evacuationBrgy = evacuationBrgy;
    }

    public String getEvacuationName() {
        return evacuationName;
    }

    public void setEvacuationName(String evacuationName) {
        this.evacuationName = evacuationName;
    }

    public String getEvacuationAddress() {
        return evacuationAddress;
    }

    public void setEvacuationAddress(String evacuationAddress) {
        this.evacuationAddress = evacuationAddress;
    }

    public String getEvacuationLongitude() {
        return evacuationLongitude;
    }

    public void setEvacuationLongitude(String evacuationLongitude) {
        this.evacuationLongitude = evacuationLongitude;
    }

    public String getEvacuationLatitude() {
        return evacuationLatitude;
    }

    public void setEvacuationLatitude(String evacuationLatitude) {
        this.evacuationLatitude = evacuationLatitude;
    }

    public String getEvacuationCity() {
        return evacuationCity;
    }

    public void setEvacuationCity(String evacuationCity) {
        this.evacuationCity = evacuationCity;
    }

    public String getEvacuationBrgy() {
        return evacuationBrgy;
    }

    public void setEvacuationBrgy(String evacuationBrgy) {
        this.evacuationBrgy = evacuationBrgy;
    }
}
