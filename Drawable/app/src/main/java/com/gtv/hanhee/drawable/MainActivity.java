package com.gtv.hanhee.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    ImageView img3;
    ScrollView scrollView;
    CustomDrawableView mCustomDrawableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomDrawableView = new CustomDrawableView(this);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.mainLayout);
        img3 = findViewById(R.id.img3);
        scrollView = findViewById(R.id.scrollView);

//       addAView();
       addCustomDrawable();

    }

    private void addCustomDrawable() {

        MyDrawable mydrawing = new MyDrawable();
        img3.setImageDrawable(mydrawing);
    }

    private void addAView() {

        // add image from resource
        ImageView img = new ImageView(MainActivity.this);
        img.setImageResource(R.drawable.nikita);
        img.setAdjustViewBounds(true);
        img.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(img,0);

        // add image from drawable
        Resources res = getResources();
        Drawable drawableImg2 = ResourcesCompat.getDrawable(res, R.drawable.nikita, null);
        ImageView img2 = new ImageView(MainActivity.this);
        img2.setImageDrawable(drawableImg2);
        img2.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.addView(img2);

        // add image transition
        Resources res2 = getResources();
        TransitionDrawable transitionDrawable = (TransitionDrawable) ResourcesCompat.getDrawable(res2, R.drawable.transition_drawable, null);
        img3.setImageDrawable(transitionDrawable);
        transitionDrawable.startTransition(1000);
    }
}
