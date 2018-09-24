package com.gtv.hanhee.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txtDate, txtTime;
    ImageButton btnDate, btnTime;
    Calendar calendar=Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        
                
    }

    private void addEvents() {
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyhienthudate();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyhienthitimepick();
            }
        });
    }

    private void xulyhienthitimepick() {
        TimePickerDialog.OnTimeSetListener listener= new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(Calendar.HOUR_OF_DAY,i);
                calendar.set(Calendar.MINUTE,i1);
                txtTime.setText(simpleDateFormat2.format(calendar.getTime()));

            }
        };
        TimePickerDialog timePickerDialog= new TimePickerDialog(
                MainActivity.this,
                listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true);
        timePickerDialog.show();
    };


    private void xulyhienthudate() {
        DatePickerDialog.OnDateSetListener callback= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR,i);
                calendar.set(Calendar.MONTH,i1);
                calendar.set(Calendar.DAY_OF_MONTH,i2);
                txtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };
        DatePickerDialog datePickerDialog= new DatePickerDialog(
                MainActivity.this,
                callback,
                calendar.get(Calendar.YEAR),
                 calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void addControls() {
        txtDate = (TextView) findViewById(R.id.txtDate);
        txtTime = (TextView) findViewById(R.id.txtTime);
        btnDate = (ImageButton) findViewById(R.id.btnDate);
        btnTime = (ImageButton) findViewById(R.id.btnTime);

        calendar = Calendar.getInstance();
        txtDate.setText(simpleDateFormat.format(calendar.getTime()));
        txtTime.setText(simpleDateFormat2.format(calendar.getTime()));

    }
}
