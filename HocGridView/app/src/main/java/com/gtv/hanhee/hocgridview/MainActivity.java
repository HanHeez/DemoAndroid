package com.gtv.hanhee.hocgridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.gtv.hanhee.hocgridview.com.gtv.hanhee.adapter.HinhAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gvHinh;
    ArrayList<Integer> dsHinh;
    HinhAdapter hinhadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        gvHinh = (GridView) findViewById(R.id.gvHinh);
        dsHinh = new ArrayList<>();
        dsHinh.add(R.drawable.bo);
        dsHinh.add(R.drawable.br);
        dsHinh.add(R.drawable.bs);
        dsHinh.add(R.drawable.bt);
        dsHinh.add(R.drawable.bw);
        dsHinh.add(R.drawable.by);
        dsHinh.add(R.drawable.bz);
        dsHinh.add(R.drawable.ca);
        dsHinh.add(R.drawable.cd);
        hinhadapter = new HinhAdapter (
                MainActivity.this,
                R.layout.item,
                dsHinh
        );
        gvHinh.setAdapter(hinhadapter);
    }
}
