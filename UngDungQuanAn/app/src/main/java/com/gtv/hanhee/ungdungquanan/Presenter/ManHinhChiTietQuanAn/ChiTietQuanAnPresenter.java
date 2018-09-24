package com.gtv.hanhee.ungdungquanan.Presenter.ManHinhChiTietQuanAn;

import android.util.Log;
import android.widget.TextView;

import com.gtv.hanhee.ungdungquanan.Interface.ChiTietQuanAnInterface;
import com.gtv.hanhee.ungdungquanan.Model.WifiQuanAnModel;

import java.util.ArrayList;
import java.util.List;

public class ChiTietQuanAnPresenter {

    WifiQuanAnModel wifiQuanAnModel;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public ChiTietQuanAnPresenter() {
        wifiQuanAnModel = new WifiQuanAnModel();
        wifiQuanAnModelList = new ArrayList<>();
    }
    public void HienThiDanhSachWifiQuanAn(String maquanan, final TextView txtTenWifi, final TextView txtMatkhauWifi, final TextView txtNgaydangWifi) {

               ChiTietQuanAnInterface chiTietQuanAnInterface = new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifiQuanAn(WifiQuanAnModel wifiQuanAnModel) {
                wifiQuanAnModelList.add(wifiQuanAnModel);
                txtTenWifi.setText(wifiQuanAnModel.getTen());
                txtMatkhauWifi.setText(wifiQuanAnModel.getMatkhau());
                txtNgaydangWifi.setText(wifiQuanAnModel.getNgaydang());


            }
        };
        wifiQuanAnModel.LayDanhSachWifiQuanAn(maquanan,chiTietQuanAnInterface);

    }
}
