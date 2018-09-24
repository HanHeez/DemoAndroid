package com.gtv.hanhee.hocbroadcastreceiver_manifest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Administrator on 4/24/2018.
 */

public class InternetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null)
        {
            xulythongbao(context);
        } else {

        }
    }

    private void xulythongbao(Context context) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Co thong bao")
                .setContentText("Moi ban nhan de cap nhat version");

        Intent resultIntent = new Intent(context, MainActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

//         Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//         mBuilder.setSound(uri);

        Uri newSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.laugh1);
        mBuilder.setSound(newSound);


        NotificationManager mNotifiMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifiMgr.notify(114,mBuilder.build());
    }
}
