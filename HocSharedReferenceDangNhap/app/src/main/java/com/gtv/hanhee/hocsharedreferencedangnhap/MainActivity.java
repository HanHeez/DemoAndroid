package com.gtv.hanhee.hocsharedreferencedangnhap;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername,txtPassword;
    Button btnDangnhap,btnThoat;
    CheckBox chkLuuthongtin;

    String tenThongtindangnhap ="login";

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
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        chkLuuthongtin = (CheckBox) findViewById(R.id.chkLuuthongtin);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences=getSharedPreferences(tenThongtindangnhap,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Username",txtUsername.getText().toString());
        editor.putString("Password",txtPassword.getText().toString());
        editor.putBoolean("SAVE",chkLuuthongtin.isChecked());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences=getSharedPreferences(tenThongtindangnhap,MODE_PRIVATE);
        String username = preferences.getString("Username","");
        String password = preferences.getString("Password","");
        boolean save = preferences.getBoolean("SAVE",false);

        if (save) {
            txtUsername.setText(username);
            txtPassword.setText(password);
        }
    }
}
