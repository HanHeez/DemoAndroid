package com.gtv.hanhee.servicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnStop, btnStartIntentService, btnLayDuLieu;
    DemoBoundService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStartIntentService = (Button) findViewById(R.id.btnStartIntentService);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnStartIntentService.setOnClickListener(this);
        btnLayDuLieu = (Button) findViewById(R.id.btnLayService);
        btnLayDuLieu.setOnClickListener(this);

        service = new DemoBoundService();
        Intent intent = new Intent(MainActivity.this,DemoBoundService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DemoBoundService.LocalBinder localBinder = (DemoBoundService.LocalBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,MusicService.class);
        switch (v.getId()) {
            case R.id.btnStart:

                startService(intent);
                break;
            case R.id.btnStop:
                stopService(intent);
                break;
            case R.id.btnStartIntentService:
                Intent intent1 = new Intent(MainActivity.this,DemoIntentService.class);
                startService(intent1);
                break;
            case R.id.btnLayService:
                Toast.makeText(this, service.XinChao(), Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
