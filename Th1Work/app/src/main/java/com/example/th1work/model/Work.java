package com.example.th1work.model;

public class Work {
    private int genderImg;
    private String name, detail, date;

    public Work() {
    }

    public Work(int genderImg, String name, String detail, String date) {
        this.genderImg = genderImg;
        this.name = name;
        this.detail = detail;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGenderImg() {
        return genderImg;
    }

    public void setGenderImg(int genderImg) {
        this.genderImg = genderImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
