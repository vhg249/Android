package com.example.serviceexample.model;

public class Service {
    private int imgService, amount, price;
    private String measure;

    public Service() {
    }

    public Service(int imgService, int amount, int price, String measure) {
        this.imgService = imgService;
        this.amount = amount;
        this.price = price;
        this.measure = measure;
    }

    public int getImgService() {
        return imgService;
    }

    public void setImgService(int imgService) {
        this.imgService = imgService;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
