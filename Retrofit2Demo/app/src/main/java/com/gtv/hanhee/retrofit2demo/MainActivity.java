package com.gtv.hanhee.retrofit2demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDangky, btnDangnhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangky = findViewById(R.id.btnDangky);
        btnDangnhap = findViewById(R.id.btnDangnhap);

        btnDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDangky = new Intent(MainActivity.this,DangKyActivity.class);
                startActivity(iDangky);
            }
        });


    }


}
