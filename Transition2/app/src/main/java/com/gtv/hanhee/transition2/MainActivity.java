package com.gtv.hanhee.transition2;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ImageView ivProfile;
    TextView txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        ivProfile = findViewById(R.id.ivProfile);
        txtText = findViewById(R.id.txtText);

        final TransitionSet transitionSet = new TransitionSet()
                .setOrdering(TransitionSet.ORDERING_TOGETHER)
                .addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform());

        //dung cho Fragment
        Fragment fragment = new Fragment();
        fragment.setSharedElementEnterTransition(transitionSet);
        fragment.setEnterTransition(new Fade());
        fragment.setSharedElementReturnTransition(transitionSet);

//        getWindow().setSharedElementEnterTransition(transitionSet);
//        getWindow().setSharedElementExitTransition(transitionSet);

        Fade fade = new Fade(Fade.IN);
        fade.setDuration(1000);
        getWindow().setExitTransition(fade);

    }

    public void onChangeIntent(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
// Pass data object in the bundle and populate details activity.
//        intent.putExtra(DetailActivity, contact);
        Pair<View, String> p1 = Pair.create((View)ivProfile, "profile");
        Pair<View, String> p2 = Pair.create((View) txtText, "text");

//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, (View)ivProfile, "profile");
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, p1, p2);
        startActivity(intent, options.toBundle());
    }
}
