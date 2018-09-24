package com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangNhap;

import com.gtv.hanhee.ungdungquanan.View.ManHinhDangNhap.ViewDangNhap;

public class MyPresenterDangNhap implements PresenterDangNhap {

    ViewDangNhap mView;


    public MyPresenterDangNhap(ViewDangNhap mView) {
        this.mView = mView;
    }


    @Override
    public void XuLyDuLieu() {
        mView.AddControls();

    }
}
