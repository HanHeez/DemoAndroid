package com.hanhee.gtv.hocmomanhinh_truyendulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanhee.gtv.model.Sinhvien;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void xulymovaguidulieu(View view) {
        Intent intent = new Intent(MainActivity.this,Manhinh2Activity.class);
        intent.putExtra("Kieu Boolean", true);
        intent.putExtra("Kieu Char", 'x');
        intent.putExtra("Kieu int",100);
        intent.putExtra("Kieu double",15.99);

        Sinhvien sv = new Sinhvien(1,"Nguyen Van A");
        intent.putExtra("Sinh Vien",sv);
        startActivity(intent);
    }
}
