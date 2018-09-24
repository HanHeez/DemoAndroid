package com.gtv.hanhee.ungdungquanan.Presenter.ManHinhDangKy;

public interface PresenterDangKy {

    void XulyDangky();

    void KiemtraEmail(String email);

    void KiemtraPassword(String password);

    void KiemtraNhaplaiPassword(String password, String nhaplaipassword);

    void TaotaikhoanDN(String email, String password);
}
