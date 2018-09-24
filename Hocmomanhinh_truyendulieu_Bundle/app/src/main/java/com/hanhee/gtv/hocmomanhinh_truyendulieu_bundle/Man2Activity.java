package com.hanhee.gtv.hocmomanhinh_truyendulieu_bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Man2Activity extends AppCompatActivity {

    TextView txtKetqua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man2);
        addControls();

    }

    private void addControls() {
        txtKetqua = (TextView) findViewById(R.id.txtKetqua);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle thu 1");
        txtKetqua.setText(
                "X=" + bundle.getInt("X") + "\n" +
                        "D=" + bundle.getDouble("D") + "\n" +
                        "SP=" + bundle.getSerializable("SANPHAM") + "\n"         );

    }
}
