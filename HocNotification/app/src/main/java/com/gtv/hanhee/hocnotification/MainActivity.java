package com.gtv.hanhee.hocnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTao,btnDong;
    int notificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taoNotification();

            }
        });
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tatNotification();
            }
        });

    }

    private void tatNotification() {

        NotificationManager mNotifiMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifiMgr.cancel(notificationID);
    }

    private void taoNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.alarm)
                .setContentTitle("Co thong bao")
                .setContentText("Moi ban nhan de cap nhat version");

        Intent resultIntent = new Intent(this, UpdateActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

         mBuilder.setContentIntent(resultPendingIntent);

//         Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//         mBuilder.setSound(uri);

        Uri newSound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.laugh1);
        mBuilder.setSound(newSound);

         notificationID = 113;
        NotificationManager mNotifiMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifiMgr.notify(notificationID,mBuilder.build());
    }

    private void addControls() {
        btnTao = (Button) findViewById(R.id.btnTao);
        btnDong = (Button) findViewById(R.id.btnDong);
    }
}
