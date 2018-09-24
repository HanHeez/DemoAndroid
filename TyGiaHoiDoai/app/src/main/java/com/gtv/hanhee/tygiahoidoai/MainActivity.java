package com.gtv.hanhee.tygiahoidoai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.gtv.hanhee.adapter.ListCodeAdapter;
import com.gtv.hanhee.model.ListCode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvListCode;
    ArrayList<ListCode> listcode;
    ListCodeAdapter listCodeAdapter;

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
        lvListCode = (ListView) findViewById(R.id.lvListCode);
        listcode = new ArrayList<>();
        listcode.add(new ListCode(R.drawable.tl, "123", "145", "567", "572"));
        listcode.add(new ListCode(R.drawable.us, "123", "145", "567", "572"));
        listcode.add(new ListCode(R.drawable.tr, "123", "145", "567", "572");

        listCodeAdapter = new ListCodeAdapter(
                MainActivity.this,
                R.layout.item,
                listcode
        );
        lvListCode.setAdapter(listCodeAdapter);
    }
}
