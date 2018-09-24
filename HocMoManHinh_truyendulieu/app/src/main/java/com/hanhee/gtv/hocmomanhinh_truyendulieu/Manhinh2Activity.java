package com.hanhee.gtv.hocmomanhinh_truyendulieu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hanhee.gtv.model.Sinhvien;

public class Manhinh2Activity extends AppCompatActivity {

    TextView txtKetqua;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manhinh2);
        addControls();
    }

    private void addControls() {
        txtKetqua = (TextView) findViewById(R.id.txtKetqua);
        Intent intent=getIntent();
        boolean kieuBoolean = intent.getBooleanExtra("Kieu Boolean",false);
        char kieuChar = intent.getCharExtra("Kieu Char",'w');
        int kieuInt = intent.getIntExtra("Kieu int",0);
        double kieuDouble = intent.getDoubleExtra("Kieu double",0.0);

        Sinhvien sv = (Sinhvien) intent.getSerializableExtra("Sinh Vien");

        txtKetqua.setText(
                "Kieu hoolean = " + kieuBoolean + "\n" +
                "Kieu Char = " + kieuChar + "\n" +
                "Kieu Int = " + kieuInt + "\n" +
                "Kieu doi tuong = " + sv + "\n"
        );
    }
}
