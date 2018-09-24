package com.gtv.hanhee.model;

import android.text.Editable;

/**
 * Created by Administrator on 3/29/2018.
 */

public class NhanVien {
    private String Ten;
    private String thuCV;
    private int songayct;

    public NhanVien() {
    }

    public NhanVien(String ten, String thuCV, int songayct) {
        Ten = ten;
        this.thuCV = thuCV;
        this.songayct = songayct;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        this.Ten = ten;
    }

    public String getThuCV() {
        return thuCV;
    }

    public void setThuCV(String thuCV) {
        this.thuCV = thuCV;
    }

    public int getSongayct(int i) {
        return songayct;
    }

    public void setSongayct(int songayct) {
        this.songayct = songayct;
    }

    @Override
    public String toString() {
        return this.Ten +"\n Bat dau cong tac vao thu ["+this.thuCV+"]" +
                "\n So ngay cong tac du kien =  " + this.songayct;
    }
}
