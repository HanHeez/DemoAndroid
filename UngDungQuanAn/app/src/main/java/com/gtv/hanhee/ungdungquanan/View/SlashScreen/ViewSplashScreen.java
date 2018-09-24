package com.gtv.hanhee.ungdungquanan.View.SlashScreen;

import android.location.Location;

public interface ViewSplashScreen {
    void AddControls();

    void LayTenVersion();

    void TruyCapTrangDangNhap();

    void CheckPermission();

    void saveCurrentLocation(Location location);

}
