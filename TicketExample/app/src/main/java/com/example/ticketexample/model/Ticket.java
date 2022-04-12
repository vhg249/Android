package com.example.ticketexample.model;

public class Ticket {
    private String code, date, type;
    private boolean paid;

    public Ticket(String code, String date, String type, boolean paid) {
        this.code = code;
        this.date = date;
        this.type = type;
        this.paid = paid;
    }

    public Ticket() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
