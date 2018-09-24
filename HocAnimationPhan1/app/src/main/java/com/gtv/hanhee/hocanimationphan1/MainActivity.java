package com.gtv.hanhee.hocanimationphan1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button btnXoayCtrl, btnXoayMh, btn3s, btnHieuUnglv;
    Animation animation=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnXoayCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnXoayCtrl.startAnimation(animation);
            }
        });

        btnXoayMh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaymanhinh);
                LinearLayout layoutManhinh = (LinearLayout) findViewById(R.id.layoutManhinh);
                layoutManhinh.startAnimation(animation);
            }
        });
        btn3s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaynguoc3s);
                btn3s.startAnimation(animation);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        finish();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    private void addControls() {
        btnXoayCtrl = (Button) findViewById(R.id.btnXoayCtrl);
        btnXoayMh = (Button) findViewById(R.id.btnXoayMh);
        btn3s = (Button) findViewById(R.id.btn3s);
        btnHieuUnglv = (Button) findViewById(R.id.btnHieuUngLv);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.xoaycontrol);

    }
}
