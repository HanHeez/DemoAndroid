package com.gtv.hanhee.random;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtNumber;
    EditText min;
    EditText max;
    Button btnRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = (TextView) findViewById(R.id.textViewNumber);
        min = (EditText) findViewById(R.id.Min);
        max = (EditText) findViewById(R.id.Max);
        btnRandom = (Button) findViewById(R.id.buttonRandom);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                String minStr = min.getText().toString();
                String maxStr = max.getText().toString();
                if (minStr.isEmpty() || maxStr.length()==0) {
                    Toast.makeText(MainActivity.this, "Vui long nhap du so", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(maxStr) < Integer.parseInt(minStr) ) {
                    Toast.makeText(MainActivity.this, "Vui long nhap so thu 2 lon hon", Toast.LENGTH_SHORT).show();
                } else {
                        int range = Integer.parseInt(maxStr) - Integer.parseInt(minStr);
                        int number = random.nextInt(range+1) + Integer.parseInt(minStr);

                        txtNumber.setText(String.valueOf(number)); };
                }

        });
    }
}
