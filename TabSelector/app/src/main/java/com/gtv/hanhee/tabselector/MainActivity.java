package com.gtv.hanhee.tabselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtA,txtB;
    Button btnCong;
    ListView lvHistory;
    ArrayList<String> dsCong;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyphepcong();
            }
        });
    }

    private void xulyphepcong() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        String s =a + "+" + b + "=" + (a+b);
        dsCong.add(s);
        adapter.notifyDataSetChanged();
        txtA.setText("");
        txtB.setText("");
    }

    private void addControls() {
        TabHost tabHost= findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tab1= tabHost.newTabSpec("t1");
        tab1.setIndicator("1. Phep Cong");
        tab1.setContent(R.id.tab1);
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2= tabHost.newTabSpec("t2");
        tab2.setIndicator("2. Lich Su");
        tab2.setContent(R.id.tab2);
        tabHost.addTab(tab2);


        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        btnCong = (Button) findViewById(R.id.btnCong);
        lvHistory = (ListView) findViewById(R.id.lvHistory);
        dsCong = new ArrayList<>();
        adapter = new ArrayAdapter<String> (
            MainActivity.this,
            android.R.layout.simple_list_item_1,
            dsCong
            );
        lvHistory.setAdapter(adapter);

    }
}
