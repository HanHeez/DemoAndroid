package com.gtv.hanhee.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    CheckBox cbAndroid, cbIOS, cbPHP;
    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cbAndroid = (CheckBox) findViewById(R.id.checkBoxAndroid);
        cbIOS = (CheckBox) findViewById(R.id.checkBoxIOS);
        cbPHP = (CheckBox) findViewById(R.id.checkBoxPHP);
        btnAccept = (Button) findViewById(R.id.buttonAccept);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "Ban da chon mon hoc: \n";
                if (cbAndroid.isChecked()) {
                    subject += cbAndroid.getText() +"\n";
                }
                if (cbIOS.isChecked()) {
                    subject += cbIOS.getText() + "\n";
                }
                if (cbPHP.isChecked()) {
                    subject += cbPHP.getText() + "\n";
                }
                Toast.makeText(MainActivity.this, subject, Toast.LENGTH_SHORT).show();
            }
        });

        cbAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(MainActivity.this,"Da chon Android",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
