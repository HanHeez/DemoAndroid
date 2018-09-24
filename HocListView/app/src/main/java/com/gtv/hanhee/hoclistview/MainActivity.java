package com.gtv.hanhee.hoclistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lvThu;
    String [] arrThu;
    ArrayAdapter<String> adapterThu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        arrThu = getResources().getStringArray(R.array.arrThu);
        adapterThu = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrThu
        );
        lvThu = (ListView) findViewById(R.id.lvThu);
        lvThu.setAdapter(adapterThu);
    }

    private void addEvents() {
        lvThu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Ban chon "+ arrThu[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
