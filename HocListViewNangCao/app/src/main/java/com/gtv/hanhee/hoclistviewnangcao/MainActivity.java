package com.gtv.hanhee.hoclistviewnangcao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.gtv.hanhee.adapter.DanhBaAdapter;
import com.gtv.hanhee.model.DanhBa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDanhBa;
    ArrayList<DanhBa> dsDanhBa;
    DanhBaAdapter danhbaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        lvDanhBa = (ListView) findViewById(R.id.lvDanhBa);
        dsDanhBa = new ArrayList<>();
        dsDanhBa.add(new DanhBa(1,"Nguyen Van A","098724567"));
        dsDanhBa.add(new DanhBa(2,"Nguyen Van B","098724569"));
        dsDanhBa.add(new DanhBa(3,"Nguyen Van C","098724563")));
        danhbaAdapter = new DanhBaAdapter(
                MainActivity.this,
                R.layout.item,
                dsDanhBa
        );
        lvDanhBa.setAdapter(danhbaAdapter);
    }


    private void addEvents() {
    }

}
