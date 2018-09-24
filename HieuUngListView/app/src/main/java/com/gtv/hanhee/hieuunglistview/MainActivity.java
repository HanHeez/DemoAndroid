package com.gtv.hanhee.hieuunglistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gtv.hanhee.adapter.MyAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTen;
    ArrayList<String> dsTen;
    ArrayAdapter<String> adapterTen;

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
        lvTen = (ListView) findViewById(R.id.lvData);
        dsTen = new ArrayList<>();
        adapterTen = new ArrayAdapter<String>(
                MainActivity.this,
                R.layout.item,
                dsTen
        );
        lvTen.setAdapter(adapterTen);

        for (int i=0;i<5000;i++) {
            dsTen.add("Ten thu" +i);
        }


        adapterTen.notifyDataSetChanged();

    }
}
