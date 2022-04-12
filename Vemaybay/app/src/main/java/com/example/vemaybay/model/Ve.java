package com.example.vemaybay.model;

public class Ve {
    private String maVe, loai, ngayBay;
    private boolean isPaid;

    public Ve() {
    }

    public Ve(String maVe, String loai, String ngayBay, boolean isPaid) {
        this.maVe = maVe;
        this.loai = loai;
        this.ngayBay = ngayBay;
        this.isPaid = isPaid;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getNgayBay() {
        return ngayBay;
    }

    public void setNgayBay(String ngayBay) {
        this.ngayBay = ngayBay;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
