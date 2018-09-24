package com.gtv.hanhee.oop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvMH;
    ArrayList<String> arrayCourse;
    EditText edtMH;
    Button btnMH,btnCapNhat;
    int vitri = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMH = (ListView) findViewById(R.id.listViewMH);
        arrayCourse = new ArrayList<>();
        edtMH = (EditText) findViewById(R.id.editTextMH);
        btnMH = (Button) findViewById(R.id.buttonThem);
        btnCapNhat = (Button) findViewById(R.id.buttonCapNhat);

        arrayCourse.add("Android");
        arrayCourse.add("iOS");
        arrayCourse.add("Unity");
        arrayCourse.add("PHP");
        arrayCourse.add("ASP.NET");

        final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayCourse);

        lvMH.setAdapter(adapter);

        btnMH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monhoc = edtMH.getText().toString();
                arrayCourse.add(monhoc);
                adapter.notifyDataSetChanged();
            }
        });

        lvMH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtMH.setText(arrayCourse.get(i));
                vitri = i;
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayCourse.set(vitri,edtMH.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        lvMH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayCourse.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        lvMH.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayCourse.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
