package com.gtv.hanhee.ungdungquanan.Presenter.Wifi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.gtv.hanhee.ungdungquanan.Adapter.AdapterDanhsachWifi;
import com.gtv.hanhee.ungdungquanan.Model.WifiQuanAnModel;
import com.gtv.hanhee.ungdungquanan.Interface.ChiTietQuanAnInterface;
import com.gtv.hanhee.ungdungquanan.R;

import java.util.ArrayList;
import java.util.List;

public class CapNhatWifiPresenter {
    WifiQuanAnModel wifiQuanAnModel;
    Context context;
    List<WifiQuanAnModel> wifiQuanAnModelList;

    public CapNhatWifiPresenter(Context context) {
        wifiQuanAnModel = new WifiQuanAnModel();
        this.context = context;
    }

    public void HienThiDanhSachWifi(String maquanan, final RecyclerView recyclerView) {


        wifiQuanAnModelList = new ArrayList<>();
        ChiTietQuanAnInterface chiTietQuanAnInterface = new ChiTietQuanAnInterface() {
            @Override
            public void HienThiDanhSachWifiQuanAn(WifiQuanAnModel wifiQuanAnModel) {
                wifiQuanAnModelList.add(wifiQuanAnModel);
                AdapterDanhsachWifi adapterDanhsachWifi = new AdapterDanhsachWifi(context, R.layout.layout_wifi_chitietquanan,wifiQuanAnModelList);
                recyclerView.setAdapter(adapterDanhsachWifi);
                adapterDanhsachWifi.notifyDataSetChanged();

            }
        };
        wifiQuanAnModel.LayDanhSachWifiQuanAn(maquanan,chiTietQuanAnInterface);

    }

    public void ThemWifi(Context context, WifiQuanAnModel wifiQuanAnModel, String maquanan) {
        wifiQuanAnModel.ThemWifiQuanAn(context,wifiQuanAnModel, maquanan);
    }
}
