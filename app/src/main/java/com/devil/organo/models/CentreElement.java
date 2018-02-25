package com.devil.organo.models;

public class CentreElement {
    private String centreName, centreID, address;
    double lat,lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public CentreElement(String centreName, String centreID, String address, double lat, double lng) {
        this.centreName = centreName;
        this.centreID = centreID;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getCentreID() {
        return centreID;
    }

    public void setCentreID(String centreID) {
        this.centreID = centreID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(double lat,double lng) {
        this.lat = lat; this.lng = lng;
    }

}