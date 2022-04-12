package com.example.footballplayerexample.model;

import java.util.List;

public class Player {
    private String name, date;
    private int gender;
    private List<String> position;

    public Player() {
    }

    public Player(String name, String date, int gender, List<String> position) {
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }
}
