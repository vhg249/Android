package com.example.nganhang.model;

public class Bank {
    private String name, time;
    private int icon;
    private float interest;
    private boolean online;

    public Bank() {
    }

    public Bank(String name, String time, int icon, float interest, boolean online) {
        this.name = name;
        this.time = time;
        this.icon = icon;
        this.interest = interest;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
