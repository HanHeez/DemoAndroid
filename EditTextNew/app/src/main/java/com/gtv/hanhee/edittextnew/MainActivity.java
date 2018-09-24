package com.gtv.hanhee.edittextnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtND;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtND = (EditText) findViewById(R.id.editTextHoTen);
        btnClick = (Button) findViewById(R.id.buttonClick);
        edtND.setText("Android HanHee");
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ND = edtND.getText().toString();
                Toast.makeText(MainActivity.this, ND, Toast.LENGTH_LONG).show();
            }
        });
    }
}
