package com.gtv.hanhee.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.StateListAnimator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtAnim;
    Button btnProperty, btnView, btnDrawable, btnState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtAnim = findViewById(R.id.txtAnim);
        btnProperty = findViewById(R.id.btnProperty);
        btnView = findViewById(R.id.btnView);
        btnDrawable = findViewById(R.id.btnDrawable);
        btnState = findViewById(R.id.btnState);

        btnProperty.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnDrawable.setOnClickListener(this);
        btnState.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnProperty:
                    onClickPropertyAnim();
                break;
            case R.id.btnView:
                    onClickViewAnim();
                break;
            case R.id.btnDrawable:
                onClickDrawableAnim();
                break;
            case R.id.btnState:
                onClickStateListAnim();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void onClickStateListAnim() {
        // add statelist cho btnDrawable
        StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(MainActivity.this,R.xml.animate_scale);
        btnDrawable.setStateListAnimator(stateListAnimator);

    }

    private void onClickDrawableAnim() {

    }

    private void onClickPropertyAnim() {
//                AnimatorSet objectAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.demoanimator);
//                objectAnimator.setTarget(txtAnim);
//                objectAnimator.start();

        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();

        ObjectAnimator changeX = ObjectAnimator.ofFloat(txtAnim, "x", 0,400);
        changeX.setDuration(1000);
        changeX.addListener(new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animation) {

                                }
                            });

        ObjectAnimator changeY = ObjectAnimator.ofFloat(txtAnim, "y", 0,400);
        changeY.setDuration(1000);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(txtAnim, "alpha", 0, 1);
        alpha.setDuration(1000);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(txtAnim, "rotation", 0,360);
        rotation.setDuration(1000);

        //Holder Anim property

        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 0,3);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 0,3);
        ObjectAnimator animationCombo = ObjectAnimator.ofPropertyValuesHolder(txtAnim, pvhX, pvhY);
        animationCombo.setDuration(1000);
        animatorSet2.play(animationCombo);
//        animatorSet2.start();

        changeX.setInterpolator(new AnticipateOvershootInterpolator(6));
//        animatorSet.setInterpolator(new AnticipateOvershootInterpolator(6));
        animatorSet.play(changeX).with(changeY).with(alpha).after(animatorSet2).before(rotation);
        animatorSet.start();
    }

    private void onClickViewAnim() {
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.demoanim);
        txtAnim.startAnimation(animation);

//        AnimatorSet animatorSet = new AnimatorSet();
//        TranslateAnimation translateAnimation = new TranslateAnimation(0,300,0,0);
//        translateAnimation.setDuration(1000);
//        animatorSet.
    }
}
