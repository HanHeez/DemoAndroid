package com.gtv.hanhee.transition;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Exam2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);

        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        final LinearLayout container2 = findViewById(R.id.container2);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
        final Button button = (Button) transitionsContainer.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            //Show , hide TextView
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //Require Api 19, tự động làm hiệu ứng transition
                TransitionManager.beginDelayedTransition(container2);
                
                //Require Api 21, tự động làm hiệu ứng transition
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.RIGHT));
                visible = !visible;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);
            }

        });
    }
}
