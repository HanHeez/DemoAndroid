package com.hanhee.gtv.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String ten;
    private String phone;

    public Contact() {
    }

    public Contact(String ten, String phone) {
        this.ten = ten;
        this.phone = phone;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
