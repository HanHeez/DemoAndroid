package com.gtv.hanhee.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvHinhAnh;
    ArrayList<HinhAnh> arrayImage;
    HinhAnhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvHinhAnh = (GridView) findViewById(R.id.gridviewTen);
        arrayImage = new ArrayList<>();
        arrayImage.add(new HinhAnh("Hinh 1",R.drawable.hinh1));
        arrayImage.add(new HinhAnh("Hinh 1",R.drawable.hinh2));
        arrayImage.add(new HinhAnh("Hinh 1",R.drawable.hinh3));
        arrayImage.add(new HinhAnh("Hinh 1",R.drawable.hinh4));

       adapter = new HinhAnhAdapter(this,R.layout.dong_hinh_anh, arrayImage);
       gvHinhAnh.setAdapter(adapter);

    }
}
