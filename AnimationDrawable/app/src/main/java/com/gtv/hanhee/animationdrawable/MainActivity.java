package com.gtv.hanhee.animationdrawable;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    AnimationDrawable rocketAnimation;
    AnimatedVectorDrawable vectorAnimation;
    ImageView imgRocket;
    ImageView imgVector;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgRocket = findViewById(R.id.imgRocket);
        imgVector = findViewById(R.id.imgVector);

        //AnimatedVectorDrawable
        imgRocket.setBackgroundResource(R.drawable.animator_vector_drawable);
        vectorAnimation = (AnimatedVectorDrawable) imgVector.getDrawable();
        vectorAnimation.start();

        //AnimationDrawable
        imgRocket.setBackgroundResource(R.drawable.custom_anim_drawable);
        rocketAnimation = (AnimationDrawable) imgRocket.getBackground();

        imgRocket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rocketAnimation.start();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animate(View view) {
        ImageView v = (ImageView) view;
        Drawable d = v.getDrawable();
        if (d instanceof AnimatedVectorDrawable) {
            AnimatedVectorDrawable avd = (AnimatedVectorDrawable) d;
            avd.start();
        } else if (d instanceof AnimatedVectorDrawableCompat) {
            AnimatedVectorDrawableCompat avd = (AnimatedVectorDrawableCompat) d;
            avd.start();
        }
    }
}
