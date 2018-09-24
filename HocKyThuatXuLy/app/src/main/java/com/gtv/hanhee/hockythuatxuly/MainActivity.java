package com.gtv.hanhee.hockythuatxuly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    EditText txtA,txtB;
    Button btnCong,btnNhan,btnChia,btnAn,btnThoat;
    View.OnClickListener sukienchiase = null;
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
                XuLyPhepCong();
            }
        });
        sukienchiase = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.btnNhan) {
                    xulyphepnhan();
                }
                else if (view.getId()==R.id.btnChia) {
                    xulyphepchia();
                }

            }
        };
        btnNhan.setOnClickListener(sukienchiase);
        btnChia.setOnClickListener(sukienchiase);
        btnThoat.setOnClickListener(new MyEvent());
        btnAn.setOnLongClickListener(this);
    }

    private void addControls() {
        txtA = (EditText) findViewById(R.id.txtA);
        txtB = (EditText) findViewById(R.id.txtB);
        btnCong = (Button) findViewById(R.id.btnCong);
        btnNhan = (Button) findViewById(R.id.btnNhan);
        btnChia = (Button) findViewById(R.id.btnChia);
        btnAn = (Button) findViewById(R.id.btnAn);
        btnThoat = (Button) findViewById(R.id.btnThoat);

    }

    public void XuLyPhepCong() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a + b;
        Toast.makeText(MainActivity.this, "Tong = " + c, Toast.LENGTH_SHORT).show();
    }
    public void xulyphepnhan() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        int c = a * b;
        Toast.makeText(MainActivity.this, "Tong = " + c, Toast.LENGTH_SHORT).show();
    }
    public void xulyphepchia() {
        int a = Integer.parseInt(txtA.getText().toString());
        int b = Integer.parseInt(txtB.getText().toString());
        double c = a / b;
        Toast.makeText(MainActivity.this, "Tong = " + c, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId()==R.id.btnAn) {
            btnAn.setVisibility(View. INVISIBLE);
        }
        return false;
    }

    public class MyEvent implements View.OnClickListener, View.OnLongClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId()==R.id.btnThoat) {
                finish();
            }
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    public void xulyDoiManHinhKhac( View view) {
        Button btnMoi = new Button(MainActivity.this) {
            @Override
            public boolean performClick() {

                setContentView(R.layout.activity_main);


                addControls();
                addEvents();
                return super.performClick();
            }
        };

        btnMoi.setText("Quay Ve");
        btnMoi.setWidth(200);
        btnMoi.setHeight(200);

        setContentView(btnMoi);

    }
}
