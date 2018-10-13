package com.gtv.hanhee.transition2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DetailActivity extends AppCompatActivity {
    ImageView ivProfile;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivProfile = findViewById(R.id.ivProfile);
        linearLayout = findViewById(R.id.linearlayout);


        Fade fade = new Fade(Fade.OUT);
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });
    }
}
