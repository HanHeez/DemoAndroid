package com.gtv.hanhee.viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    float toadox1 = 0, toadox2 = 0;
    ViewFlipper viewFlipper;
    ImageView imgNext, imgPrev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgPrev = (ImageView) findViewById(R.id.imgPrev);
        viewFlipper.setInAnimation(MainActivity.this,android.R.anim.fade_in);
        viewFlipper.setOutAnimation(MainActivity.this,android.R.anim.fade_out);

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);

        //viewFlipper.getDisplayedChild() tra ve vi tri view dang hien thi;
        // viewFlipper.setDisplayedChild(); set vi tri View muon hien thi;

        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showNext();
            }
        });
        imgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.showPrevious();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                toadox1 = event.getX();
                Log.d("Toa do 1",""+toadox1);

                break;
            case MotionEvent.ACTION_UP:
                toadox2 = event.getX();
                Log.d("Toa do 2",""+toadox2);
                if (toadox2 > toadox1) {
                    viewFlipper.showPrevious();
                    //prev
                } else if (toadox2 < toadox1){
                    viewFlipper.showNext();
                    //mext
                }
                break;

        }
        return super.onTouchEvent(event);
    }
}
