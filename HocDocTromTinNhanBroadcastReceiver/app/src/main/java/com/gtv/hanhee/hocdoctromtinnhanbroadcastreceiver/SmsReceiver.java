package com.gtv.hanhee.hocdoctromtinnhanbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 4/24/2018.
 */

public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        Object [] arrPdus = (Object []) bundle.get("pdus");
        for (int i =0; i < arrPdus.length;i++) {
            SmsMessage smsMessage= SmsMessage.createFromPdu((byte[]) arrPdus[i]);
            String noidung = smsMessage.getMessageBody();
            String phone = smsMessage.getOriginatingAddress();
            long time=smsMessage.getTimestampMillis();

            Date date= new Date(time);
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
            String dateFormatted = formatter.format(date);
            Toast.makeText(context, "So phone: "+ phone + "\n Noi dung: " + noidung + "\n Nhan luc: " + dateFormatted, Toast.LENGTH_SHORT).show();
        }
    }
}
