package com.gtv.hanhee.image;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imagehinh;
    RelativeLayout layout;
    ArrayList<Integer> arrayImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagehinh = (ImageView) findViewById(R.id.imageView);
        layout = (RelativeLayout) findViewById((R.id.manhinh));

        arrayImage = new ArrayList<>();
        arrayImage.add(R.drawable.adrwallpaper);
        arrayImage.add(R.drawable.charbns);
        arrayImage.add(R.drawable.ic_launcher_background);

        Random random = new Random();
        int vitri = random.nextInt(arrayImage.size());
        layout.setBackgroundResource(arrayImage.get(vitri));

        //        man.setBackgroundColor(Color.rgb(123,44,58));
//        man.setBackgroundResource(R.drawable.charbns);
//        imagehinh.setImageResource(R.drawable.ic_launcher_background);
    }
}
