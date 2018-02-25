package com.devil.organo.models;

public class NotificationElement {
    private String date,message;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationElement(String date, String message) {
        this.date = date;
        this.message = message;
    }
}