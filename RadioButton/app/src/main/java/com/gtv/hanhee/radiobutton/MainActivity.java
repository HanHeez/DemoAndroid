package com.gtv.hanhee.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgTime;
    RadioButton rdSang, rdTrua, rdChieu;
    Button xacnhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rgTime = (RadioGroup) findViewById(R.id.radioGroupTime);
        rdSang = (RadioButton) findViewById(R.id.radioButtonSang);
        rdChieu = (RadioButton) findViewById(R.id.radioButtonChieu);
        rdTrua = (RadioButton) findViewById(R.id.radioButtonTrua);
        xacnhan = (Button) findViewById(R.id.buttonXacNhan);

        xacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdSang.isChecked()) {
                    Toast.makeText(MainActivity.this, rdSang.get(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        rgTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButtonSang:
                        Toast.makeText(MainActivity.this,"Ban chon sang",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonTrua:
                        Toast.makeText(MainActivity.this,"Ban chon trua",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButtonChieu:
                        Toast.makeText(MainActivity.this,Integer.toString(R.id.radioButtonChieu),Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
