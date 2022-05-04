package com.example.ticket.model;

public class Ticket {
    private String code, date, type;
    private boolean isPaid;

    public Ticket() {
    }

    public Ticket(String code, String date, String type, boolean isPaid) {
        this.code = code;
        this.date = date;
        this.type = type;
        this.isPaid = isPaid;
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
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
