package com.gtv.hanhee.animationcrossfade;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    View containView;
    View loadingSpinner;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        containView = findViewById(R.id.content);
        loadingSpinner = findViewById(R.id.loading_spinner);

        containView.setVisibility(View.GONE);
        loadingSpinner.setVisibility(View.VISIBLE);

        duration = getResources().getInteger(android.R.integer.config_shortAnimTime);

        crossfade();
    }

    private void crossfade() {
        containView.setAlpha(0f);
        containView.setVisibility(View.VISIBLE);
        containView.animate().alpha(1f).setDuration(2000).setListener(null);

        loadingSpinner.animate().alpha(0f).setDuration(2000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loadingSpinner.setVisibility(View.GONE);
            }
        });
    }
}
