package com.gtv.hanhee.ungdungquanan.View.Wifi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gtv.hanhee.ungdungquanan.Model.WifiQuanAnModel;
import com.gtv.hanhee.ungdungquanan.Presenter.Wifi.CapNhatWifiPresenter;
import com.gtv.hanhee.ungdungquanan.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PopupWifiActivity extends AppCompatActivity {

    EditText edtTenWifi,edtMatkhauWifi;
    Button btnDongY;
    CapNhatWifiPresenter capNhatWifiPresenter;
    String maquanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup_wifi);

        maquanan = getIntent().getStringExtra("maquanan");

        edtTenWifi = (EditText) findViewById(R.id.edtTenWifi);
        edtMatkhauWifi = (EditText) findViewById(R.id.edtMatkhauWifi);
        capNhatWifiPresenter = new CapNhatWifiPresenter(this);
        btnDongY = (Button) findViewById(R.id.btnDongy);

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenwifi = edtTenWifi.getText().toString();
                String matkhauwifi = edtMatkhauWifi.getText().toString();
                if (tenwifi.trim().length()>0 && matkhauwifi.trim().length() > 0) {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String ngaydang = simpleDateFormat.format(calendar.getTime());

                    WifiQuanAnModel wifiQuanAnModel = new WifiQuanAnModel();
                    wifiQuanAnModel.setTen(tenwifi);
                    wifiQuanAnModel.setMatkhau(matkhauwifi);
                    wifiQuanAnModel.setNgaydang(ngaydang);
                    capNhatWifiPresenter.ThemWifi(PopupWifiActivity.this,wifiQuanAnModel,maquanan);
                    finish();

                } else {
                    Toast.makeText(PopupWifiActivity.this, "Vui lòng nhập đầy đủ thông tin wifi", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
