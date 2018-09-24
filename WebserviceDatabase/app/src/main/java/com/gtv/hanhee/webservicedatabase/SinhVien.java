package com.gtv.hanhee.webservicedatabase;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private int Id;
    private String Hoten;
    private int Namsinh;
    private String Diachi;

    public SinhVien() {
    }

    public SinhVien(int id, String hoten, int namsinh, String diachi) {
        Id = id;
        Hoten = hoten;
        Namsinh = namsinh;
        Diachi = diachi;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String hoten) {
        Hoten = hoten;
    }

    public int getNamsinh() {
        return Namsinh;
    }

    public void setNamsinh(int namsinh) {
        Namsinh = namsinh;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }
}
