package com.example.bth1.model;

public class Tour extends Model{
    private String lichTrinh;
    private String thoiGian;
    private int img;

    public Tour(String lichTrinh, String thoiGian, int img) {
        super();
        this.lichTrinh = lichTrinh;
        this.thoiGian = thoiGian;
        this.img = img;
    }

    public String getLichTrinh() {
        return lichTrinh;
    }

    public void setLichTrinh(String lichTrinh) {
        this.lichTrinh = lichTrinh;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
