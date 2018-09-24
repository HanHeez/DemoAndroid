package com.gtv.hanhee.firebasenotification;

import android.app.Service;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("kiem tra",remoteMessage.getFrom()+" - "+remoteMessage.getData()
                + " - " +remoteMessage.getNotification().getBody()
                + " - " + remoteMessage.getNotification().getTitle());
    }
}
