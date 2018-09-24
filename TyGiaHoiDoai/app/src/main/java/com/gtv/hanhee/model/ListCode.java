package com.gtv.hanhee.model;

import java.io.Serializable;

/**
 * Created by Administrator on 3/29/2018.
 */

public class ListCode implements Serializable {
    private int imgFlag;
    private String MuaTM;
    private String BanTM;
    private String MuaCK;
    private String BanCK;

    public ListCode(int imgFlag, String muaTM, String banTM, String muaCK, String banCK) {
        this.imgFlag = imgFlag;
        MuaTM = muaTM;
        BanTM = banTM;
        MuaCK = muaCK;
        BanCK = banCK;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }

    public String getMuaTM() {
        return MuaTM;
    }

    public void setMuaTM(String muaTM) {
        MuaTM = muaTM;
    }

    public String getBanTM() {
        return BanTM;
    }

    public void setBanTM(String banTM) {
        BanTM = banTM;
    }

    public String getMuaCK() {
        return MuaCK;
    }

    public void setMuaCK(String muaCK) {
        MuaCK = muaCK;
    }

    public String getBanCK() {
        return BanCK;
    }

    public void setBanCK(String banCK) {
        BanCK = banCK;
    }
}
