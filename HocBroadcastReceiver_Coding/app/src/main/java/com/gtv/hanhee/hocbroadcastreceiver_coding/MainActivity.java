package com.gtv.hanhee.hocbroadcastreceiver_coding;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    Button btnDangnhap,btnThoat;
    BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo() != null)
            {
                btnDangnhap.setEnabled(true);
                Toast.makeText(context, "Ban vua mo internet", Toast.LENGTH_SHORT).show();
            } else {
                btnDangnhap.setEnabled(false);
                Toast.makeText(context, "Ban vua tat internet", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(wifiReceiver,filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wifiReceiver!=null) unregisterReceiver(wifiReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
    }

    private void addControls() {
        btnDangnhap = (Button) findViewById(R.id.btnDangnhap);

    }
}
