package com.gtv.hanhee.medialocal;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnMp3,btnMp4;
    VideoView videoMp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.anh_muon);
                mediaPlayer.start();
            }
        });

        btnMp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoMp4.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hypnolize));
                videoMp4.start();

                MediaController mediaController = new MediaController(MainActivity.this);
                mediaController.setMediaPlayer(videoMp4);
                videoMp4.setMediaController(mediaController);
            }
        });
    }

    private void addControls() {
        btnMp3 = (Button) findViewById(R.id.btnMp3);
        btnMp4 = (Button) findViewById(R.id.btnMp4);
        videoMp4 = (VideoView) findViewById(R.id.videoMp4);


    }
}
