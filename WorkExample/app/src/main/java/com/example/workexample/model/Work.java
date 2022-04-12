package com.example.workexample.model;

public class Work {
    private String name, content, date;
    private int genderImg;

    public Work() {
    }

    public Work(String name, String content, String date, int genderImg) {
        this.name = name;
        this.content = content;
        this.date = date;
        this.genderImg = genderImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int isGender() {
        return genderImg;
    }

    public void setGender(int gender) {
        this.genderImg = gender;
    }
}
