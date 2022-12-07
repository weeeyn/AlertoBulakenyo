package com.activity.alertobulakenyo;

public class distanceParameter {
    float distance;
    String location;
    Double latitude,longitude;

    public distanceParameter(float distance, String location, Double latitude, Double longitude) {
        this.distance = distance;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public distanceParameter() {

    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
