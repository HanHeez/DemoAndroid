package com.gtv.hanhee.aswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch swtWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swtWifi = (Switch) findViewById(R.id.switchWifi);
        swtWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this,"Bat Wifi",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"Tat Wifi",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
