package com.gtv.hanhee.progressbar;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDownload;
    ProgressBar pgXuly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDownload = (Button) findViewById(R.id.buttonDownload);
        pgXuly = (ProgressBar) findViewById(R.id.progressBarXuly);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //10s tong thoi gian dem nguoc, 1s thoi gian lap lai 1 hanh dong
                CountDownTimer countDownTimer = new CountDownTimer(5000,500) {
                    @Override
                    public void onTick(long l) {
                        int current = pgXuly.getProgress();
                        if (current >= pgXuly.getMax()) {
                            current = 0;
                        }
                        pgXuly.setProgress(current+10);
                    }

                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "Het gio", Toast.LENGTH_SHORT).show();
                        // lap lai
                        this.start();
                    }
                };
                countDownTimer.start();
            }
        });
    }
}
