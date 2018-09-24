package com.gtv.hanhee.ungdungquanan.Presenter.SplashScreen;

import com.gtv.hanhee.ungdungquanan.View.SlashScreen.ViewSplashScreen;

public class MyPresenterSplashScreen implements PresenterSplashScreen {

    ViewSplashScreen mView;

    public MyPresenterSplashScreen(ViewSplashScreen mView) {
        this.mView = mView;
    }

    @Override
    public void XuLyDuLieu() {
        mView.CheckPermission();
        mView.AddControls();
        mView.LayTenVersion();
    }
}
