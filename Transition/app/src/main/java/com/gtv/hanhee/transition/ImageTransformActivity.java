package com.gtv.hanhee.transition;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageTransformActivity extends AppCompatActivity {
    ImageView imageView;
    boolean expanded = false;

    private final static String PACKAGE = "com.ideashower.readitlater.pro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transform);

        PackageManager pm = getPackageManager();
        PackageInfo info;
        try {
            info = pm.getPackageInfo(PACKAGE, 0);

        } catch (PackageManager.NameNotFoundException e) {
            info = null;
        }

        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        final LinearLayout container2 = findViewById(R.id.container2);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
        final Button button = (Button) transitionsContainer.findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        final TransitionSet transitionSet = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeImageTransform());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer, transitionSet);
                expanded = !expanded;
                ViewGroup.LayoutParams params = imageView.getLayoutParams();

//                params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
//                        ViewGroup.LayoutParams.WRAP_CONTENT;
//                params.width = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
//                        ViewGroup.LayoutParams.WRAP_CONTENT;

                params.height = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                        200;
                params.width = expanded ? ViewGroup.LayoutParams.MATCH_PARENT :
                        200;
                imageView.setLayoutParams(params);

                imageView.setScaleType(expanded ? ImageView.ScaleType.CENTER_CROP :
                        ImageView.ScaleType.FIT_CENTER);


            }
        });
    }
}
