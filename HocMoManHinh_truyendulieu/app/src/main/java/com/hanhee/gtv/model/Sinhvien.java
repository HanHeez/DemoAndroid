package com.hanhee.gtv.model;

import java.io.Serializable;

public class Sinhvien implements Serializable {
    private int ma;
    private String ten;

    @Override
    public String toString() {
        return this.ma +"\n" + this.ten;
    }

    public Sinhvien() {
    }

    public Sinhvien(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
