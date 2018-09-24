package com.gtv.hanhee.ungdungquanan.View.Wifi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gtv.hanhee.ungdungquanan.Presenter.Wifi.CapNhatWifiPresenter;
import com.gtv.hanhee.ungdungquanan.R;

public class CapNhatDanhSachWifiActivity extends AppCompatActivity {

    Button btnCapnhatWifi;
    RecyclerView recyclerDanhsachWifi;
    CapNhatWifiPresenter capNhatWifiPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cap_nhat_danh_sach_wifi);

    }

    private void AddControls() {
        btnCapnhatWifi = (Button) findViewById(R.id.btnCapnhatWifi);
        recyclerDanhsachWifi = (RecyclerView) findViewById(R.id.recyclerDanhsachWifi);

        final String maquanan = getIntent().getStringExtra("maquanan");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,true);
        recyclerDanhsachWifi.setLayoutManager(layoutManager);

        capNhatWifiPresenter = new CapNhatWifiPresenter(this);
        capNhatWifiPresenter.HienThiDanhSachWifi(maquanan,recyclerDanhsachWifi);

        btnCapnhatWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CapNhatDanhSachWifiActivity.this,PopupWifiActivity.class);
                intent.putExtra("maquanan",maquanan);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        AddControls();
    }
}
