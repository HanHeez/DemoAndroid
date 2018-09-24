package com.gtv.hanhee.hocdrawableanimation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnStart,btnStop;
    ImageView imghieuung;

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        AddEvents();
    }

    private void AddEvents() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.start();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.stop();
            }
        });
    }

    private void AddControls() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        imghieuung = (ImageView) findViewById(R.id.imgAnimation);
        btnStart = (Button) findViewById(R.id.btnStart);

        imghieuung.setBackgroundResource(R.drawable.hieuung);

        animationDrawable = (AnimationDrawable) imghieuung.getBackground();
    }
}
