package com.gtv.hanhee.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String trangthaicuocgoi = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        Log.d("kiemtra",trangthaicuocgoi);
    }
}
