package com.gtv.hanhee.hocspinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gtv.hanhee.model.NhanVien;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtTen,txtSoNgay;
    Button btnXacNhan;

    Spinner spThu;
    ArrayList<String> dsThu;
    ArrayAdapter<String> adapterThu;
    int lastselect=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyXacNhan();
            }
        });

        spThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lastselect=i;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void xulyXacNhan() {
        if (lastselect==-1) {
            Toast.makeText(MainActivity.this, "Chua chon ngay", Toast.LENGTH_SHORT).show();
            return;
        }
        NhanVien nv = new NhanVien();
        nv.setTen(String.valueOf(txtTen.getText()));
        nv.setThuCV(dsThu.get(lastselect));
        nv.setSongayct(Integer.parseInt(txtSoNgay.getText().toString()));
        Toast.makeText(MainActivity.this, nv.toString(), Toast.LENGTH_SHORT).show();
    }

    private void addControls() {
        txtTen = (EditText) findViewById(R.id.txtTen);
        txtSoNgay = (EditText) findViewById(R.id.txtSoNgay);
        btnXacNhan = (Button) findViewById(R.id.btnXacNhan);
        spThu = (Spinner) findViewById(R.id.spThu);
        dsThu = new ArrayList<>();
        dsThu.add("Thu 2");dsThu.add("Thu 3");dsThu.add("Thu 4");dsThu.add("Thu 5");dsThu.add("Thu 6");
        dsThu.add("Thu 7");dsThu.add("Thu 8");

        adapterThu = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_spinner_item,
                dsThu
        );
        adapterThu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spThu.setAdapter(adapterThu);
    }
}
