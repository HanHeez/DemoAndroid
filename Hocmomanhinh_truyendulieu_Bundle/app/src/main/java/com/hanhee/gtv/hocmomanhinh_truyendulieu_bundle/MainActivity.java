package com.hanhee.gtv.hocmomanhinh_truyendulieu_bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanhee.gtv.model.SanPham;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void xulymodulieubundle(View view) {
        Intent intent = new Intent(MainActivity.this,Man2Activity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("X", 113);
        bundle.putDouble("D",114.115);
        SanPham coca = new SanPham(1,"Coca",1500.5);
        bundle.putSerializable("SANPHAM",coca);
        intent.putExtra("Bundle thu 1",bundle);

        startActivity(intent);
    }
}
