package com.gtv.hanhee.animationmoveaview;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.support.animation.DynamicAnimation;
import android.support.animation.FlingAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtView,txtView2,txtView3, txtView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = findViewById(R.id.txtView);
        txtView2 = findViewById(R.id.txtView2);
        txtView3 = findViewById(R.id.txtView3);
        txtView4 = findViewById(R.id.txtView4);

        //Move a view
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(txtView, "translationX", 200f);
//        animator1.setDuration(2000);
//        animator1.start();

        //Move a view have PathInterpolator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            PathInterpolator pathInterpolator = new PathInterpolator(0.4f,0f,1f,1f);

            ObjectAnimator animator2 = ObjectAnimator.ofFloat(txtView2, "translationX", 200f);
            animator2.setInterpolator(pathInterpolator);
            animator2.setDuration(2000);
            animator2.start();

            //Define own Path

            Path path = new Path();
            path.arcTo(50f, 50f, 200f, 200f, 270f, -180f, true);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(txtView3,"x","y",path);
            animator3.setDuration(2000);
            animator3.start();

            // have pathInterpolator

            ObjectAnimator animator4 = ObjectAnimator.ofFloat(txtView4,"x","y",path);
            animator4.setInterpolator(pathInterpolator);
            animator4.setDuration(2000);
            animator4.start();
        }

//        flingAnimation();
        springAnimation();

    }

    private void springAnimation() {
        final SpringAnimation anim = new SpringAnimation(txtView, DynamicAnimation.Y, 10)
                .setStartVelocity(10000);
        anim.getSpring().setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        anim.start();
    }

    private void flingAnimation() {
        FlingAnimation flingAnimation = new FlingAnimation(txtView, DynamicAnimation.ROTATION_X);
        flingAnimation.setStartVelocity(10000)
                .setMinValue(0)
                .setMaxValue(10000)
                .setFriction(0.1f)
                .start();
    }
}
