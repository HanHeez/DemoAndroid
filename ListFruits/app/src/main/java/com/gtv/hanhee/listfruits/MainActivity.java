package com.gtv.hanhee.listfruits;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTraiCay;
    ArrayList<TraiCay> arrayTraiCay;
    TraiCayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTraiCay = (ListView) findViewById(R.id.listViewTraiCay);
        arrayTraiCay = new ArrayList<>();

        arrayTraiCay.add(new TraiCay("Dau tay", "Dau tay da lat", R.drawable.hinh1));
        arrayTraiCay.add(new TraiCay("Mang Cau", "Mang Cau", R.drawable.hinh1));
        arrayTraiCay.add(new TraiCay("Cai gi do", "Ko biet cai gi", R.drawable.hinh1));
        arrayTraiCay.add(new TraiCay("1234", "56789", R.drawable.hinh1));


        adapter = new TraiCayAdapter(this,R.layout.dong_trai_cay, arrayTraiCay);
        lvTraiCay.setAdapter(adapter);

    }
}
