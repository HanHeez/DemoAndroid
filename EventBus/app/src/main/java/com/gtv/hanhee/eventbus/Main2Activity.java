package com.gtv.hanhee.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main2Activity extends AppCompatActivity {
    TextView txtHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtHello = findViewById(R.id.txtHello);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void onMessageEvent(MessageEvent event) {
        Log.d("kiemtra","dang chay Mess 1 " + event.getMessage());
        txtHello.setText(event.getMessage());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void onMessageEvent2(MessageEvent2 event) {
        Log.d("kiemtra","dang chay Mess 2 " + event.getMessage());
        txtHello.setText(event.getMessage());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}

