package com.gtv.hanhee.drawableclip2;

import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgHinh;
    Button btnClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        btnClip = (Button) findViewById(R.id.buttonClip);
        imgHinh.setImageLevel(1000);

        final ClipDrawable clipDrawable = (ClipDrawable) imgHinh.getDrawable();

        btnClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int currentLevel = clipDrawable.getLevel();
                        if (currentLevel >= 10000) {
                            currentLevel = 0;
                        }
                        imgHinh.setImageLevel(currentLevel + 1000);
                        handler.postDelayed(this, 500);
                    }
                }, 1000);


            }
        });
    }
}
