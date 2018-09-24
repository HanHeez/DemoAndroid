package com.gtv.hanhee.ungdungquanan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ThanhVienModel implements Parcelable {
    public static final Creator<ThanhVienModel> CREATOR = new Creator<ThanhVienModel>() {
        @Override
        public ThanhVienModel createFromParcel(Parcel in) {
            return new ThanhVienModel(in);
        }

        @Override
        public ThanhVienModel[] newArray(int size) {
            return new ThanhVienModel[size];
        }
    };

    //    public String getMathanhvien() {
//        return mathanhvien;
//    }
//
//    public void setMathanhvien(String mathanhvien) {
//        this.mathanhvien = mathanhvien;
//    }
    String hoten, hinhanh;

    public ThanhVienModel() {
    }

    protected ThanhVienModel(Parcel in) {
        hoten = in.readString();
        hinhanh = in.readString();
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hoten);
        dest.writeString(hinhanh);
    }
}
