package com.example.bth1.model;

public class Model {
    public static int idCount = 0;
    private int id = 0;
    private String lichTrinh;

    public Model() {
        idCount++;
        id = idCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLichTrinh() {
        return lichTrinh;
    }

    public void setLichTrinh(String lichTrinh) {
        this.lichTrinh = lichTrinh;
    }
}
