package com.devil.organo.models;

public class RecentElement {
    private String date, donorID, donorName, centreName, donated;
    public RecentElement(String date, String donorID, String donorName, String centreName, String donated) {
        this.date = date;
        this.donorID = donorID;
        this.donorName = donorName;
        this.centreName = centreName;
        this.donated = donated;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDonorID() {
        return donorID;
    }

    public void setDonorID(String donorID) {
        this.donorID = donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getDonated() {
        return donated;
    }

    public void setDonated(String donated) {
        this.donated = donated;
    }
}