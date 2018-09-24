package com.gtv.hanhee.projectungdungbanhang.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    public int id,giasp,idsp;
    public String tensp,hinhanhsp,motasp;


    public Sanpham(int id, int giasp, String tensp, String hinhanhsp, String motasp, int idsp) {
        this.id = id;
        this.giasp = giasp;
        this.idsp = idsp;
        this.tensp = tensp;
        this.hinhanhsp = hinhanhsp;
        this.motasp = motasp;
        this.giasp = giasp;
    }

    public Sanpham() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiasp() {
        return giasp;
    }

    public void setGiasp(int giasp) {
        this.giasp = giasp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
    }

    public String getMotasp() {
        return motasp;
    }

    public void setMotasp(String motasp) {
        this.motasp = motasp;
    }
}
